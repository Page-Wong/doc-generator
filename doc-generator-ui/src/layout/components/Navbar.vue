<template>
  <div class="navbar">
    <h1>
      <router-link to="/">iAide</router-link>
    </h1>
    <el-menu
      class="topmenu-container"
      mode="horizontal"
      :default-active="activeMenu"
    >
      <el-menu-item index="/generator">文档生成</el-menu-item>
      <el-menu-item index="2">API</el-menu-item>
      <el-menu-item index="3">博客</el-menu-item>
      <el-menu-item index="4">源码</el-menu-item>
      <el-menu-item index="5">更新日志</el-menu-item>
    </el-menu>
    <div class="right-menu">
      <el-dropdown
        class="avatar-container right-menu-item hover-effect"
        trigger="click"
      >
        <div class="avatar-wrapper">
          <img :src="avatar" class="user-avatar" />
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/user/profile">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link>
          <el-dropdown-item divided @click.native="logout">
            <span>退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  components: {},
  computed: {
    ...mapGetters(["avatar", "name"]),
    // 默认激活的菜单
    activeMenu() {
      const path = this.$route.path;

      return path;
    },
  },
  methods: {
    async logout() {
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

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  margin-bottom: 5px;
  height: 60px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);

  h1 {
    float: left;
    margin: 0;
    font-size: 32px;
    font-weight: 400;
    padding-left: 15px;
    padding-top: 10px;

    a {
      color: #409eff;
    }
  }

  .topmenu-container {
    position: absolute;
    left: 120px;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 60px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
