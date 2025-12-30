import { useEffect, useMemo, useState } from "react";
import { Device, DeviceType, UpdateDeviceInput } from "../../types";
import { ErrorBanner } from "../common/ErrorBanner";

const DEVICE_TYPES: DeviceType[] = ["TEMP", "HUMIDITY", "CO2", "MOTION"];

type Props = {
  device: Device | null;
  saving: boolean;
  error: string | null;
  onClose: () => void;
  onSave: (input: UpdateDeviceInput) => void;
};

export default function EditDeviceModal({
  device,
  saving,
  error,
  onClose,
  onSave,
}: Props) {
  const [name, setName] = useState("");
  const [building, setBuilding] = useState("");
  const [room, setRoom] = useState("");
  const [type, setType] = useState<DeviceType>("TEMP");

  useEffect(() => {
    if (!device) return;
    setName(device.name);
    setBuilding(device.building);
    setRoom(device.room);
    setType(device.type);
  }, [device?.id]);

  const canSave = useMemo(() => {
    return (
      !!name.trim() && !!building.trim() && !!room.trim() && !saving && !!device
    );
  }, [name, building, room, saving, device]);

  if (!device) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center px-4 py-8">
      <div
        className="absolute inset-0 bg-slate-900/40"
        onClick={onClose}
      />
      <div className="relative z-10 w-full max-w-lg rounded-xl bg-white p-6 shadow-xl">
        <div className="mb-4 flex items-center justify-between">
          <div>
            <h3 className="text-lg font-semibold text-slate-900">
              Edit device
            </h3>
            <p className="text-sm text-slate-500">{device.id}</p>
          </div>
          <button
            className="rounded-md px-2 py-1 text-sm text-slate-500 transition hover:bg-slate-100"
            onClick={onClose}
          >
            Close
          </button>
        </div>

        {error && (
          <div className="mb-4">
            <ErrorBanner message={error} />
          </div>
        )}

        <form
          className="space-y-4"
          onSubmit={(e) => {
            e.preventDefault();
            if (!canSave) return;
            onSave({
              name: name.trim(),
              building: building.trim(),
              room: room.trim(),
              type,
            });
          }}
        >
          <div className="space-y-2">
            <label className="text-sm font-semibold text-slate-700">Name</label>
            <input
              className="h-10 w-full rounded-md border border-slate-200 bg-white px-3 text-sm shadow-sm outline-none transition focus:border-slate-400 focus:ring-2 focus:ring-slate-200"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>

          <div className="space-y-2">
            <label className="text-sm font-semibold text-slate-700">
              Building
            </label>
            <input
              className="h-10 w-full rounded-md border border-slate-200 bg-white px-3 text-sm shadow-sm outline-none transition focus:border-slate-400 focus:ring-2 focus:ring-slate-200"
              value={building}
              onChange={(e) => setBuilding(e.target.value)}
            />
          </div>

          <div className="space-y-2">
            <label className="text-sm font-semibold text-slate-700">Room</label>
            <input
              className="h-10 w-full rounded-md border border-slate-200 bg-white px-3 text-sm shadow-sm outline-none transition focus:border-slate-400 focus:ring-2 focus:ring-slate-200"
              value={room}
              onChange={(e) => setRoom(e.target.value)}
            />
          </div>

          <div className="space-y-2">
            <label className="text-sm font-semibold text-slate-700">Type</label>
            <select
              className="h-10 w-full rounded-md border border-slate-200 bg-white px-3 text-sm shadow-sm outline-none transition focus:border-slate-400 focus:ring-2 focus:ring-slate-200"
              value={type}
              onChange={(e) => setType(e.target.value as DeviceType)}
            >
              {DEVICE_TYPES.map((t) => (
                <option key={t} value={t}>
                  {t}
                </option>
              ))}
            </select>
          </div>

          <div className="flex items-center justify-end gap-3 pt-2">
            <button
              type="button"
              className="h-10 rounded-md border border-slate-200 bg-white px-4 text-sm font-semibold text-slate-700 shadow-sm transition hover:bg-slate-50"
              onClick={onClose}
            >
              Cancel
            </button>
            <button
              type="submit"
              className="h-10 rounded-md bg-slate-900 px-4 text-sm font-semibold text-white shadow-sm transition hover:bg-slate-800 disabled:cursor-not-allowed disabled:opacity-50"
              disabled={!canSave}
            >
              {saving ? "Saving…" : "Save changes"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
