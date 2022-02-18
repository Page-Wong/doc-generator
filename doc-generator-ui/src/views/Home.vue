<template>
  <div class="home">
    这是主页home
    <el-button @click="test">test</el-button>
    <el-button @click="logout">logout</el-button>
    <div>{{ msg }}</div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Home",
  data() {
    return {
      msg: "",
    };
  },
  methods: {
    test() {
      request({
        url: "/test",
        method: "get",
      }).then((result) => {
        this.msg = result;
      });
    },
    logout() {
      this.$confirm("确定注销并退出系统吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$store.dispatch("Logout").then(() => {
            location.href = "/login";
          });
        })
        .catch(() => {});
    },
  },
};
</script>
