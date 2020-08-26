module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:3003'
      }
    }
  },
  outputDir: '../src/main/resources/static',
  "transpileDependencies": [
    "vuetify"
  ],
  productionSourceMap: false
}