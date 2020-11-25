<template>
  <el-container class="home_container">
    <el-header height="7%">Header</el-header>
    <el-container>
      <el-aside width="10%">Aside</el-aside>
      <el-container>
        <el-main>
          <HelloWorld/>
        </el-main>
        <el-footer height="3%">Footer</el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld
  }
}
</script>

<style scoped lang="less">

.home_container {
  height: 100%;
  display: flex;

}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
}

.el-footer {
  text-align: center;
  background-color: #F0F8FF;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
}

.el-main {
  background-color: #F8F8FF;
}
</style>
