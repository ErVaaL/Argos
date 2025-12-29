import type { MeasurementType } from "../types";

type Props = {
  disabled: boolean;
  type: MeasurementType | "";
  from: string;
  to: string;
  loading: boolean;
  setType: (v: MeasurementType | "") => void;
  setFrom: (v: string) => void;
  setTo: (v: string) => void;
  onLoad: () => void;
};

export default function MeasurementsFilters({
  disabled,
  type,
  from,
  to,
  loading,
  setType,
  setFrom,
  setTo,
  onLoad,
}: Props) {
  return (
    <div className="grid grid-cols-1 gap-3 md:grid-cols-5">
      <select
        className="h-10 rounded-md border border-slate-200 bg-white px-3 text-sm shadow-sm outline-none transition focus:border-slate-400 focus:ring-2 focus:ring-slate-200"
        value={type}
        onChange={(e) => setType(e.target.value as MeasurementType | "")}
        disabled={disabled}
      >
        <option value="">Any type</option>
        <option value="TEMP">TEMP</option>
        <option value="HUMIDITY">HUMIDITY</option>
        <option value="CO2">CO2</option>
        <option value="MOTION">MOTION</option>
      </select>

      <input
        className="h-10 rounded-md border border-slate-200 bg-white px-3 text-sm shadow-sm outline-none transition focus:border-slate-400 focus:ring-2 focus:ring-slate-200"
        placeholder="From (ISO timestamp)"
        value={from}
        onChange={(e) => setFrom(e.target.value)}
        disabled={disabled}
      />

      <input
        className="h-10 rounded-md border border-slate-200 bg-white px-3 text-sm shadow-sm outline-none transition focus:border-slate-400 focus:ring-2 focus:ring-slate-200"
        placeholder="To (ISO timestamp)"
        value={to}
        onChange={(e) => setTo(e.target.value)}
        disabled={disabled}
      />

      <button
        className="h-10 rounded-md bg-slate-900 px-4 text-sm font-semibold text-white shadow-sm transition hover:bg-slate-800 disabled:cursor-not-allowed disabled:opacity-50"
        disabled={disabled || loading}
        onClick={onLoad}
      >
        {loading ? "Loading…" : "Load"}
      </button>

      <div className="hidden md:block" />
    </div>
  );
}
