import { useState } from "react";
import type { Measurement, MeasurementType, Page } from "../types";
import { gql } from "../lib/gql";
import { MEASUREMENTS_QUERY } from "../lib/queries";

type MeasurementsResp = { measurements: Page<Measurement> };

export const useMeasurements = (args: {
  gqlUrl: string;
  token: string | null;
  deviceId: string | null;
}) => {
  const { gqlUrl, token, deviceId } = args;

  const [type, setType] = useState<MeasurementType | "">("");
  const [from, setFrom] = useState("");
  const [to, setTo] = useState("");

  const [page, setPage] = useState(0);
  const [items, setItems] = useState<Measurement[]>([]);
  const [total, setTotal] = useState(0);

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  async function load(nextPage = 0) {
    if (!deviceId) return;
    setLoading(true);
    setError(null);
    try {
      const filter: any = { deviceId };
      if (type) filter.type = type;
      if (from.trim()) filter.from = from.trim();
      if (to.trim()) filter.to = to.trim();

      const data = await gql<MeasurementsResp>(
        gqlUrl,
        MEASUREMENTS_QUERY,
        {
          filter,
          page: {
            page: nextPage,
            size: 20,
            sortBy: "timestamp",
            sortDirection: "DESC",
          },
        },
        token,
      );

      setItems(data.measurements.content);
      setTotal(data.measurements.totalElements);
      setPage(nextPage);
    } catch (e: any) {
      setError(e.message ?? String(e));
    } finally {
      setLoading(false);
    }
  }

  function reset() {
    setItems([]);
    setTotal(0);
    setPage(0);
    setType("");
    setFrom("");
    setTo("");
    setError(null);
  }

  return {
    type,
    from,
    to,
    items,
    total,
    page,
    loading,
    error,
    setType,
    setFrom,
    setTo,
    setPage,
    load,
    reset,
  };
};
