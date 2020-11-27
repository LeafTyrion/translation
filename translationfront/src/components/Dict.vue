<template>
  <el-card class="box-card">
    <el-row :gutter="20">
      <!--搜索区域-->
      <el-col :span="7">
        <el-input placeholder="请输入内容" v-model="queryInfo.query">
          <el-button slot="append" icon="el-icon-search" @click="getDictList"></el-button>
        </el-input>
      </el-col>
      <!--按钮区域-->
      <el-col :span="4">
        <el-button type="primary" @click="addDialogVisible=true">添加字典</el-button>
      </el-col>
    </el-row>
    <!--列表区域-->
    <el-table :data="dictList" border stripe>
      <el-table-column type="index" label="#"/>
      <el-table-column label="字典ID" prop="id"/>
      <el-table-column label="歌词原文" prop="originChar"/>
      <el-table-column label="歌词音译" prop="translations[0].translatedChar"/>
      <!--操作区域-->
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <el-tooltip effect="dark" content="编辑" placement="top" :enterable="false">
            <el-button @click="editDict(scope.row)" type="primary" icon="el-icon-edit"
                       size="mini"/>
          </el-tooltip>
          <el-tooltip effect="dark" content="删除" placement="top" :enterable="false">
            <el-button @click="deleteDict(scope.row)" type="danger" icon="el-icon-delete"
                       size="mini"/>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>

    <!--添加对话框-->
    <el-dialog
        title="添加字典"
        :before-close="addDialogClosed"
        :visible.sync="addDialogVisible"
        width="500px">
      <!--主体内容区域-->
      <el-form :model="dictForm" :rules="dictFormRules" ref="addFormRef" label-width="100px">
        <el-form-item label="歌词原文" prop="originChar">
          <el-input v-model="dictForm.originChar"/>
        </el-form-item>
        <el-form-item label="歌词音译" prop="translatedChar">
          <el-input v-model="dictForm.translatedChar"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogClosed">取 消</el-button>
                <el-button type="primary" @click="addDict">确 定</el-button>
            </span>
    </el-dialog>

    <!--编辑对话框-->
    <el-dialog
        title="修改字典"
        :before-close="editDialogClosed"
        :visible.sync="editDialogVisible"
        width="500px">
      <!--主体内容区域-->
      <el-form :model="dictForm" :rules="dictFormRules" ref="editFormRef" label-width="100px">
        <el-form-item label="歌词原文" prop="originChar">
          <el-input v-model="dictForm.originChar"/>
        </el-form-item>
        <el-form-item label="歌词音译" prop="translatedChar">
          <el-input v-model="dictForm.translatedChar"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogClosed">取 消</el-button>
                <el-button type="primary" @click="updateDict">确 定</el-button>
            </span>
    </el-dialog>

    <!--分页区域-->
    <el-pagination @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="queryInfo.page"
                   :page-sizes="[5,10,15,20]"
                   :page-size="queryInfo.size"
                   layout="total,sizes,prev,pager,next,jumper"
                   :total="total"/>
  </el-card>
</template>

<script>
export default {
  name: "Dict",
  data() {
    return {
      queryInfo: {
        page: 1,
        size: 10,
        query: '',
      },
      total: 0,
      dictList: [],

      dictForm: {
        oid: 0,
        tid: 0,
        originChar: '',
        translatedChar: ''
      },

      // 控制对话框打开关闭
      addDialogVisible: false,
      editDialogVisible: false,

      dictFormRules: {
        originChar: [
          {required: true, message: '请输入歌词原文', trigger: 'blur'},
        ],
        translatedChar: [
          {required: true, message: '请输入歌词音译', trigger: 'blur'},
        ]
      },
    }
  },
  created() {
    this.getDictList();
  },
  methods: {
    // 监听pageSize每页显示的条数改变事件
    handleSizeChange(newSize) {
      this.queryInfo.size = newSize;
      this.getDictList();
    },
    // 监听页码值改变事件
    handleCurrentChange(newPage) {
      this.queryInfo.page = newPage;
      this.getDictList();
    },

    //对话框点击取消
    addDialogClosed() {
      // 重置内容
      this.addDialogVisible = false;
      this.$refs.addFormRef.resetFields();
      this.getDictList();
    },
    editDialogClosed() {
      this.editDialogVisible = false;
      this.$refs.editFormRef.resetFields();
      this.getDictList();
    },

    async getDictList() {
      const result = await this.$http.get(
          "https://39.105.4.73:8088/origin/allOrigin",
          {params: this.queryInfo});
      console.log(result);
      if (result.status !== 200) {
        return this.$message.error("获取字典列表失败")
      }
      this.dictList = result.data.content;
      this.total = result.data.totalElements;
    },

    addDict() {
      // 表单预校验
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return;
        try {
          const result = await this.$http.post(
              "https://39.105.4.73:8088/origin/addOrigin",
              this.dictForm);
          if (result.status == 200) {
            this.$message.success("添加字典成功");
            this.addDialogClosed();
          }
        } catch (e) {
          return this.$message.error("添加字典失败，请检查歌词原文是否已经存在");
        }
      });
    },

    async editDict(row) {
      this.editDialogVisible = true;
      const result = await this.$http.get(
          "https://39.105.4.73:8088/origin/getOriginById",
          {params: {id: row.id}});
      this.dictForm.oid = result.data.id;
      this.dictForm.tid = result.data.translations[0].id;
      this.dictForm.originChar = result.data.originChar;
      this.dictForm.translatedChar = result.data.translations[0].translatedChar;
    },

    updateDict() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return;
        const result = await this.$http.post(
            "https://39.105.4.73:8088/origin/updateOrigin",
            this.dictForm);

        if (result.data === true) {
          this.editDialogClosed();
          return this.$message.success("修改字典成功");
        }
        return this.$message.error("添加字典失败，请检查歌词原文是否已经存在");

      });

    },

    async deleteDict(row) {
      // 弹框再次确认
      const result = await this.$confirm('是否确认删除此字典？', '提示', {
            cancelButtonText: '取消',
            confirmButtonText: '确认',
            type: 'warning'
          }
      ).catch(error => {
        return error;
      });
      if (result !== 'confirm')
        return this.$message.info("取消删除");
      const {data} = await this.$http.get("https://39.105.4.73:8088/origin/deleteOrigin", {params: {id: row.id}});
      if (data === true) {
        await this.getDictList();
        return this.$message.success("删除成功");
      }
      return this.$message.error("删除失败");
    }
  }
}
</script>

<style scoped>

</style>