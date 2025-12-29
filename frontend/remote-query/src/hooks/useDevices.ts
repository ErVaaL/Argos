import { useEffect, useMemo, useState } from "react";
import { Device, DeviceType, Page } from "../types";
import { DEVICES_QUERY } from "../lib/queries";
import { gql } from "../lib/gql";

type DevicesResponse = {
  devices: Page<Device>;
};

export const useDevices = (args: { gqlUrl: string; token: string | null }) => {
  const { gqlUrl, token } = args;

  const [building, setBuilding] = useState("");
  const [room, setRoom] = useState("");
  const [type, setType] = useState<DeviceType | "">("");
  const [activeOnly, setActiveOnly] = useState(false);

  const [page, setPage] = useState(0);
  const [items, setItems] = useState<Device[]>([]);
  const [total, setTotal] = useState(0);

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const filter = useMemo(() => {
    const f: any = {};
    if (building.trim()) f.building = building.trim();
    if (room.trim()) f.room = room.trim();
    if (type) f.type = type;
    if (activeOnly) f.active = true;
    return f;
  }, [building, room, type, activeOnly]);

  const refresh = async (nextPage = page) => {
    setLoading(true);
    setError(null);
    try {
      const data = await gql<DevicesResponse>(
        gqlUrl,
        DEVICES_QUERY,
        {
          filter,
          page: {
            page: nextPage,
            size: 10,
            sortBy: "name",
            sortDirection: "ASC",
          },
        },
        token,
      );
      setItems(data.devices.content);
      setTotal(data.devices.totalElements);
      setPage(nextPage);
    } catch (e: any) {
      setError(e.message ?? String(e));
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    refresh(0);
  }, [gqlUrl, token, filter]);

  return {
    building,
    room,
    type,
    activeOnly,
    items,
    total,
    page,
    loading,
    error,
    setBuilding,
    setRoom,
    setType,
    setActiveOnly,
    setPage,
    refresh,
  };
};
