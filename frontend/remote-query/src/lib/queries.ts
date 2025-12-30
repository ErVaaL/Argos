export const DEVICES_QUERY = /* GraphQL */ `
  query Devices($filter: DeviceFilterInput, $page: PageRequestInput) {
    devices(filter: $filter, page: $page) {
      content {
        id
        name
        type
        building
        room
        active
      }
      totalElements
      page
      size
    }
  }
`;

export const MEASUREMENTS_QUERY = /* GraphQL */ `
  query Measurements($filter: MeasurementFilterInput, $page: PageRequestInput) {
    measurements(filter: $filter, page: $page) {
      content {
        id
        deviceId
        type
        value
        sequenceNumber
        timestamp
      }
      totalElements
      page
      size
    }
  }
`;

export const UPDATE_DEVICE_MUTATION = /* GraphQL */ `
  mutation UpdateDevice($id: ID!, $input: UpdateDeviceInput!) {
    updateDevice(id: $id, input: $input) {
      id
      name
      type
      building
      room
      active
    }
  }
`;

export const DELETE_DEVICE_MUTATION = /* GraphQL */ `
  mutation DeleteDevice($id: ID!) {
    deleteDevice(id: $id)
  }
`;
