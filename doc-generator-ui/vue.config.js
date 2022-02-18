module.exports = {
  devServer: {
    host: "localhost",
    port: 8081,
    proxy: {
      [process.env.VUE_APP_BASE_API]: {
        target: "http://localhost:8080",
        changeOrigin: true,
        pathRewrite: {
          ["^" + process.env.VUE_APP_BASE_API]: "",
        },
      },
    },
  },
};
