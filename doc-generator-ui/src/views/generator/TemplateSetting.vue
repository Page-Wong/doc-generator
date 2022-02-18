<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>第二步 Word模板选择（支持多个模板）</span>
      <el-button class="header-button" type="primary" @click="handleSelectFile"
        >选择Word模板</el-button
      >
      <input
        ref="upload"
        type="file"
        accept=".docx"
        hidden="hidden"
        @change="handleFileChange"
      />
    </div>
    <el-table
      v-show="columns.length > 0"
      :data="rows"
      ref="multipleTable"
      height="290"
      tooltip-effect="dark"
      style="width: 100%"
    >
      <el-table-column prop="__filename" label="模板文件" width="300">
      </el-table-column>
      <el-table-column label="变量">
        <template v-for="col in columns">
          <el-table-column
            sortable
            :show-overflow-tooltip="true"
            :prop="col.key"
            :label="col.title"
            :key="col.key"
            :width="col.width"
          >
            <template slot-scope="scope">
              <i
                v-if="scope.row[col.key]"
                class="el-icon-check"
                :title="`word模板存在{{${col.title}}}`"
              ></i>
              <i
                v-if="!scope.row[col.key]"
                class="el-icon-close"
                :title="`word模板不存在{{${col.title}}}`"
              ></i>
            </template>
          </el-table-column>
        </template>
      </el-table-column>
    </el-table>
    <el-empty v-show="columns.length == 0" description="暂无数据"></el-empty>
  </el-card>
</template>

<script>
import { readParam } from "@/api/generator";

export default {
  name: "TemplateSetting",
  props: {
    tableColumns: {
      type: Array,
      default: () => [],
    },
    tableRows: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      columns: this.tableColumns,
      rows: this.tableRows,
      files: [],
    };
  },
  watch: {
    tableColumns: {
      immediate: true,
      handler(value) {
        this.columns = value;
      },
    },
  },
  methods: {
    handleSelectFile() {
      this.$refs.upload.dispatchEvent(new MouseEvent("click"));
    },
    handleFileChange(e) {
      const file = e.target.files[0];
      this.files = [...this.files, file];
      readParam(file).then((res) => {
        if (res.success) {
          let row = {
            __filename: file.name,
          };
          this.tableColumns.forEach((element) => {
            row[element.key] = res.data.includes(`{{${element.title}}}`);
          });
          this.rows.push(row);
        } else {
          this.$message.error(res.message);
        }
      });

      this.$refs.upload.value = "";
    },
    getFiles() {
      return this.files;
    },
  },
};
</script>

<style>
.header-button {
  float: right;
  margin-top: -0.75rem;
}
</style>
