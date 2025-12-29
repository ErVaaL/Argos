import type { Measurement } from "../types";
import { Pagination } from "./Pagination";

type Props = {
  selectedName: string | null;
  items: Measurement[];
  total: number;
  page: number;
  pageSize: number;
  loading: boolean;
  hasSelection: boolean;
  onPrev: () => void;
  onNext: () => void;
};

export default function MeasurementsTable({
  selectedName,
  items,
  total,
  page,
  pageSize,
  loading,
  hasSelection,
  onPrev,
  onNext,
}: Props) {
  return (
    <div>
      <div className="mb-3 text-sm text-slate-500">
        {hasSelection
          ? `Measurements for ${selectedName}`
          : "Select a device above"}
      </div>

      <div className="overflow-x-auto">
        <table className="w-full border-separate border-spacing-0 text-sm">
          <thead className="bg-slate-50 text-left text-xs font-semibold uppercase tracking-wide text-slate-600">
            <tr className="[&>th]:px-3 [&>th]:py-3">
              <th className="rounded-tl-lg">Timestamp</th>
              <th>Type</th>
              <th>Value</th>
              <th className="rounded-tr-lg">Seq</th>
            </tr>
          </thead>

          <tbody className="divide-y divide-slate-200">
            {items.map((m) => (
              <tr
                key={m.id}
                className="odd:bg-white even:bg-slate-50/60 transition hover:bg-slate-100"
              >
                <td className="px-3 py-3 font-mono text-xs text-slate-700">
                  {m.timestamp}
                </td>
                <td className="px-3 py-3 text-slate-700">{m.type}</td>
                <td className="px-3 py-3 font-semibold text-slate-900">
                  {m.value}
                </td>
                <td className="px-3 py-3 text-slate-700">
                  {m.sequenceNumber ?? "-"}
                </td>
              </tr>
            ))}

            {!loading && hasSelection && items.length === 0 && (
              <tr>
                <td
                  colSpan={4}
                  className="px-3 py-8 text-center text-sm text-slate-500"
                >
                  No measurements found
                </td>
              </tr>
            )}

            {!hasSelection && (
              <tr>
                <td
                  colSpan={4}
                  className="px-3 py-8 text-center text-sm text-slate-500"
                >
                  Select a device
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
