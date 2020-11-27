<template>
  <el-container class="home_container">
    <el-header height="7%">Header</el-header>
    <el-container>
      <el-aside width="130px">
        <el-menu
            class="el-menu-vertical-demo"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            :collapse-transition="false"
            :router="true"
            :default-active="activePath?activePath:'/index'">
          <!--二级菜单-->
          <el-menu-item
              :index="menuItem.path"
              v-for="menuItem in (menuList)"
              :key="menuItem.id"
              @click="saveNavState(menuItem.path)">
            <i :class="menuItem.icon"/>
            <span slot="title">{{ menuItem.name }}</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <router-view/>
        </el-main>
        <!--        <el-footer height="3%">Copyright © 2020-Forever</el-footer>-->
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  data() {
    return {
      menuList: [
        {
          id: 1,
          path: '/translation',
          icon: 'el-icon-s-home',
          name: '转换工具'
        },
        {
          id: 2,
          path: '/dict',
          icon: 'el-icon-s-home',
          name: '歌词字典'
        }
      ],
      isCollapse: true,
      activePath: ""
    };
  },
  created() {
    this.activePath = window.sessionStorage.getItem("activePath");
  },
  methods: {
    saveNavState(activePath) {
      window.sessionStorage.setItem("activePath", activePath);
      this.activePath = activePath;
    },
  }
}
</script>

<style scoped lang="less">

.home_container {
  height: 100%;
  //display: flex;

}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
}

//.el-footer {
//  //height: 100px;
//  line-height: 40px;
//  position: center;
//  bottom: 0;
//  width: 100%;
//  text-align: center;
//  font-family: Arial, serif;
//  font-size: 12px;
//  letter-spacing: 1px;
//}

.el-aside {
  background-color: #545c64;
  color: #333;
  //text-align: center;
}

.el-main {
  background-color: #F8F8FF;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
