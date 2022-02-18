<template>
  <el-container>
    <el-header><navbar /></el-header>
    <el-main><app-main /></el-main>
  </el-container>
</template>

<script>
import { Navbar, AppMain } from "./components";

export default {
  name: "layout",
  components: {
    Navbar,
    AppMain,
  },
  computed: {},
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
@import "../styles/mixin.scss";
</style>
