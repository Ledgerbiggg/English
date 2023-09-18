const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:9999', // 设置你的后端服务器地址
        // target: 'http://ledger-code.buzz:9999', // 设置你的后端服务器地址
        changeOrigin: true, // 允许跨域
        pathRewrite: {
          '^/api': '/' // 如果后端接口没有以 '/api' 开头，可以去掉这一行
        }
      }
    }
  },
  transpileDependencies: true,
  lintOnSave: false
})
