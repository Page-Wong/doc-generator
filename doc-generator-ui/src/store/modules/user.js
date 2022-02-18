import { login, logout, getInfo } from "@/api/login";
import { getToken, setToken, removeToken } from "@/utils/auth";

const state = {
  token: getToken(),
  name: "",
  avatar: "",
};

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token;
  },
  SET_NAME: (state, name) => {
    state.name = name;
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar;
  },
};

const actions = {
  // 登录
  Login({ commit }, userInfo) {
    const username = userInfo.username.trim();
    const password = userInfo.password;
    const code = userInfo.code;
    const uuid = userInfo.uuid;
    return new Promise((resolve, reject) => {
      login(username, password, code, uuid)
        .then((res) => {
          setToken(res.data);
          commit("SET_TOKEN", res.data);
          resolve();
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // 获取用户信息
  GetInfo({ commit }) {
    return new Promise((resolve, reject) => {
      getInfo()
        .then((res) => {
          const user = res.data;
          const avatar =
            user.avatar == ""
              ? require("@/assets/images/profile.jpg")
              : process.env.VUE_APP_BASE_API + user.avatar;
          commit("SET_NAME", user.userName);
          commit("SET_AVATAR", avatar);
          resolve(res);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // 退出系统
  Logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token)
        .then(() => {
          commit("SET_TOKEN", "");
          removeToken();
          resolve();
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};

export default {
  state,
  mutations,
  actions,
};
