import type { Device } from "../types";
import { Pagination } from "./Pagination";

type Props = {
  devices: Device[];
  total: number;
  page: number;
  pageSize: number;
  loading: boolean;
  selectedId: string | null;
  onSelect: (d: Device) => void;
  onPrev: () => void;
  onNext: () => void;
};

export default function DevicesTable({
  devices,
  total,
  page,
  pageSize,
  loading,
  selectedId,
  onSelect,
  onPrev,
  onNext,
}: Props) {
  return (
    <div>
      <div className="overflow-x-auto">
        <table className="w-full border-separate border-spacing-0 text-sm">
          <thead className="bg-slate-50 text-left text-xs font-semibold uppercase tracking-wide text-slate-600">
            <tr className="[&>th]:px-3 [&>th]:py-3">
              <th className="rounded-tl-lg">Name</th>
              <th>Type</th>
              <th>Building</th>
              <th>Room</th>
              <th className="rounded-tr-lg">Active</th>
            </tr>
          </thead>

          <tbody className="divide-y divide-slate-200">
            {devices.map((d) => (
              <tr
                key={d.id}
                className={[
                  "cursor-pointer transition",
                  "odd:bg-white even:bg-slate-50/60 hover:bg-slate-100",
                  selectedId === d.id
                    ? "bg-amber-50 ring-1 ring-inset ring-amber-200"
                    : "",
                ].join(" ")}
                onClick={() => onSelect(d)}
              >
                <td className="px-3 py-3 font-semibold text-slate-900">
                  {d.name}
                </td>
                <td className="px-3 py-3 text-slate-700">{d.type}</td>
                <td className="px-3 py-3 text-slate-700">{d.building}</td>
                <td className="px-3 py-3 text-slate-700">{d.room}</td>
                <td className="px-3 py-3">
                  <span
                    className={[
                      "inline-flex items-center rounded-full px-2 py-0.5 text-xs font-semibold",
                      d.active
                        ? "bg-emerald-100 text-emerald-700"
                        : "bg-slate-100 text-slate-600",
                    ].join(" ")}
                  >
                    {d.active ? "Active" : "Inactive"}
                  </span>
                </td>
              </tr>
            ))}

            {!loading && devices.length === 0 && (
              <tr>
                <td
                  colSpan={5}
                  className="px-3 py-8 text-center text-sm text-slate-500"
                >
                  No devices found
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      <div className="mt-3">
        <Pagination
          page={page}
          pageSize={pageSize}
          total={total}
          loading={loading}
          onPrev={onPrev}
          onNext={onNext}
        />
      </div>
    </div>
  );
}
