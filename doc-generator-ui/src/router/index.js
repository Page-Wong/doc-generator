import Vue from "vue";
import VueRouter from "vue-router";
import Layout from "../layout/Layout";

Vue.use(VueRouter);

const routes = [
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/Login.vue"),
    meta: {
      ignoreLogin: true, //忽略登录验证
    },
  },
  {
    path: "",
    component: Layout,
    redirect: "/generator",
    children: [
      {
        path: "/home",
        name: "Home",
        component: () => import("@/views/Home"),
        meta: {
          title: "首页",
        },
      },
      {
        path: "/about",
        name: "About",
        component: () => import("@/views/About"),
        meta: {
          title: "关于",
        },
      },
      {
        path: "/generator",
        name: "Generator",
        component: () => import("@/views/generator/Index"),
        meta: {
          title: "文档生成",
        },
      },
    ],
  },
  // {
  //   path: "",
  //   name: "主页",
  //   component: () => import("../views/Home.vue"),
  //   meta: { title: "home", noCache: true },
  // },
  // {
  //   path: "/about",
  //   name: "About",
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () =>
  //     import(/* webpackChunkName: "about" */ "../views/About.vue"),
  // },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
