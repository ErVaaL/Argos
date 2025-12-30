export type DeviceType = "TEMP" | "HUMIDITY" | "CO2" | "MOTION";
export type MeasurementType = DeviceType;

export type Device = {
  id: string;
  name: string;
  type: DeviceType;
  building: string;
  room: string;
  active: boolean;
};

export type UpdateDeviceInput = {
  name?: string;
  type?: DeviceType;
  building?: string;
  room?: string;
  active?: boolean;
};

export type Measurement = {
  id: string;
  deviceId: string;
  type: MeasurementType;
  value: number;
  sequenceNumber: number;
  timestamp: string;
};

export type Page<T> = {
  content: T[];
  totalElements: number;
  page: number;
  size: number;
};
