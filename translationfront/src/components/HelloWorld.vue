<template>
  <el-row :gutter="30" type="flex" justify="center">
    <el-col :span="11">
      <el-form ref="form" :model="formOrigin" style="margin-top: 2%">
        <el-form-item>
          <el-input class="input_origin" type="textarea" v-model="formOrigin.origin"
                    resize="none" placeholder="请输入歌词原文，每个单词之间必须用空格隔开"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="success" @click="translate">转换</el-button>
      <el-button type="danger" @click="reset">重置</el-button>
    </el-col>
    <el-col :span="11">
      <el-form ref="form" :model="formTranslate" style="margin-top: 2%">
        <el-form-item>
          <el-input class="input_translation" type="textarea" v-model="formTranslate.translate" resize="none"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="save">保存</el-button>
    </el-col>
  </el-row>

</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      formOrigin: {
        origin: ''
      },
      formTranslate: {
        translate: ''
      }
    }
  },
  created() {
  },
  methods: {
    async translate() {
      const result = await this.$http.post(
          "http://127.0.0.1:8080/origin/getTranslationByOrigin",
          this.formOrigin);
      this.formTranslate.translate=result.data;
      console.log(result);
    },
    reset() {
      console.log('submit!');
    },
    save() {
      console.log('submit!');
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">

.el-row {
  height: 82vh;
}

::v-deep .input_origin .el-textarea__inner {
  display: flex;
  height: 80vh;
  font-size: 18px;
  font-family: "微软雅黑", serif;
}

::v-deep .input_translation .el-textarea__inner {
  display: flex;
  height: 80vh;
  font-size: 18px;
  font-family: "微软雅黑", serif;
  font-style: italic;
}

</style>
