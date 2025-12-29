import type { DeviceType } from "../types";

type Props = {
  state: {
    building: string;
    room: string;
    type: DeviceType | "";
    activeOnly: boolean;
    setBuilding: (building: string) => void;
    setRoom: (room: string) => void;
    setType: (type: DeviceType | "") => void;
    setActiveOnly: (activeOnly: boolean) => void;
    refresh: (page?: number) => void;
    loading: boolean;
  };
};

export const DeviceFilters = ({ state }: Props) => {
  return (
    <div className="grid m-4 w-full grid-cols-1 gap-3 md:grid-cols-6">
      <input
        className="h-10 rounded-md border border-slate-200 bg-white px-3 text-sm shadow-sm outline-none transition focus:border-slate-400 focus:ring-2 focus:ring-slate-200"
        placeholder="Building"
        value={state.building}
        onChange={(e) => state.setBuilding(e.target.value)}
      />

      <input
        className="h-10 rounded-md border border-slate-200 bg-white px-3 text-sm shadow-sm outline-none transition focus:border-slate-400 focus:ring-2 focus:ring-slate-200"
        placeholder="Room"
        value={state.room}
        onChange={(e) => state.setRoom(e.target.value)}
      />

      <select
        className="h-10 rounded-md border border-slate-200 bg-white px-3 text-sm shadow-sm outline-none transition focus:border-slate-400 focus:ring-2 focus:ring-slate-200"
        value={state.type}
        onChange={(e) => state.setType(e.target.value as DeviceType | "")}
      >
        <option value="">Any type</option>
        <option value="TEMP">TEMP</option>
        <option value="HUMIDITY">HUMIDITY</option>
        <option value="CO2">CO2</option>
        <option value="MOTION">MOTION</option>
      </select>

      <label className="flex h-10 items-center gap-2 rounded-md border border-slate-200 bg-white px-3 text-sm shadow-sm">
        <input
          type="checkbox"
          className="h-4 w-4 rounded border-slate-300 text-slate-900 focus:ring-slate-200"
          checked={state.activeOnly}
          onChange={(e) => state.setActiveOnly(e.target.checked)}
        />
        Active only
      </label>

      <button
        className="h-10 rounded-md bg-slate-900 px-4 text-sm font-semibold text-white shadow-sm transition hover:bg-slate-800 disabled:cursor-not-allowed disabled:opacity-50"
        onClick={() => state.refresh(0)}
        disabled={state.loading}
      >
        {state.loading ? "Loading…" : "Refresh"}
      </button>

      <div className="hidden md:block" />
    </div>
  );
};
