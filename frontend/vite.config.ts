import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import tsconfigPaths from 'vite-tsconfig-paths';
import svgr from '@svgr/rollup';
// https://vitejs.dev/config/
export default defineConfig({
  server: {
    port: 3000,
  },
  define: {
    'process.env': {
      VITE_ENV: process.env.VITE_ENV,
    },
  },
  plugins: [react(), tsconfigPaths(), svgr()],
});
