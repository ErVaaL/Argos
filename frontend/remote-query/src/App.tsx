import { useEffect, useState } from "react";
import "./index.css";
import { useDevices } from "./hooks/useDevices";
import { Device } from "./types";
import { useMeasurements } from "./hooks/useMeasurements";
import { ErrorBanner } from "./components/ErrorBanner";
import { DeviceFilters } from "./components/DeviceFilters";
import DevicesTable from "./components/DeviceTable";
import MeasurementsFilters from "./components/MeasurementsFilters";
import MeasurementsTable from "./components/MeasurementTable";

export default function App(props: {
  apiBase?: string;
  accessToken?: string | null;
}) {
  const apiBase = props.apiBase ?? "http://localhost:80/api";
  const token = props.accessToken ?? null;

  const gqlUrl = `${apiBase}/resource/graphql`;

  const devices = useDevices({ gqlUrl, token });
  const [selected, setSelected] = useState<Device | null>(null);

  const meas = useMeasurements({
    gqlUrl,
    token,
    deviceId: selected?.id ?? null,
  });

  useEffect(() => {
    meas.reset();
    if (selected) void meas.load(0);
  }, [selected?.id]);

  const error = devices.error ?? meas.error;

  return (
    <div className="p-3 space-y-6">
      <div>
        <h2 className="text-2xl font-bold">Devices and Measurements:</h2>
      </div>

      {error && <ErrorBanner message={error} />}

      <section className="rounded-lg border bg-white p-10 shadow-sm">
        <DeviceFilters state={devices} />
        <DevicesTable
          devices={devices.items}
          total={devices.total}
          page={devices.page}
          pageSize={10}
          loading={devices.loading}
          selectedId={selected?.id ?? null}
          onSelect={setSelected}
          onPrev={() => devices.refresh(Math.max(0, devices.page - 1))}
          onNext={() => devices.refresh(devices.page + 1)}
        />
      </section>

      <section className="rounded-xl border bg-white p-4 shadow-sm space-y-3">
        <MeasurementsFilters
          disabled={!selected}
          type={meas.type}
          from={meas.from}
          to={meas.to}
          loading={meas.loading}
          setType={meas.setType}
          setFrom={meas.setFrom}
          setTo={meas.setTo}
          onLoad={() => meas.load(0)}
        />
        <MeasurementsTable
          selectedName={selected?.name ?? null}
          items={meas.items}
          total={meas.total}
          page={meas.page}
          pageSize={20}
          loading={meas.loading}
          hasSelection={!!selected}
          onPrev={() => meas.load(Math.max(0, meas.page - 1))}
          onNext={() => meas.load(meas.page + 1)}
        />
      </section>
    </div>
  );
}
