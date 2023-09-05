import { fileURLToPath, URL } from 'node:url'

import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default ({ mode }: any) => {
  process.env = { ...process.env, ...loadEnv(mode, process.cwd()) }
  console.log(process.env.VITE_API_URL)
  return defineConfig({
    plugins: [vue()],
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      }
    },
    server: {
      proxy: {
        '/api': {
          target: process.env.VITE_API_URL,
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, '')
        }
      }
    }
  })
}


// 為讓config能吃到環境變數故改成上列寫法
// export default defineConfig({
//   plugins: [vue()],
//   resolve: {
//     alias: {
//       '@': fileURLToPath(new URL('./src', import.meta.url))
//     }
//   },
//   server: {
//     proxy: {
//       '/api': {
//         target: 'http://localhost:8080/',
//         changeOrigin: true,
//         rewrite: (path) => path.replace(/^\/api/, '')
//       }
//     }
//   }
// })
