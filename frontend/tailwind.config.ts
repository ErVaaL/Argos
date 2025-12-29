import type { Config } from "tailwindcss";

export default {
  content: [
    "./host/src/**/*.{ts,tsx}",
    "./remote-query/src/**/*.{ts,tsx}",
    "./remote-report/src/**/*.{ts,tsx}",
  ],
  theme: { extend: {} },
  plugins: [],
} satisfies Config;
