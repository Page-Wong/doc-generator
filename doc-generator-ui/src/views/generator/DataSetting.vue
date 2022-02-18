<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>第一步 Excel数据源导入</span
      ><span v-show="rows.length > 0">（共{{ rows.length }}条）</span>
      <el-button class="header-button" type="primary" @click="handleSelectFile"
        >选择 Excel 数据</el-button
      >
      <input
        ref="upload"
        type="file"
        accept=".xls, .xlsx"
        hidden="hidden"
        @change="handleFileChange"
      />
    </div>
    <el-table
      v-show="rows.length > 0"
      :data="rows"
      ref="multipleTable"
      height="290"
      tooltip-effect="dark"
      style="width: 100%"
    >
      <el-table-column label="序号" width="62px" type="index">
      </el-table-column>
      <template v-for="col in columns">
        <el-table-column
          sortable
          :show-overflow-tooltip="true"
          :prop="col.key"
          :label="col.title"
          :key="col.key"
          :width="col.width"
        >
        </el-table-column>
      </template>
    </el-table>
    <el-empty v-show="rows.length == 0" description="暂无数据"></el-empty>
  </el-card>
</template>

<script>
var XLSX = require("xlsx");

export default {
  name: "DataSetting",
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
      file: undefined,
    };
  },
  methods: {
    handleSelectFile() {
      this.$refs.upload.dispatchEvent(new MouseEvent("click"));
    },
    handleFileChange(e) {
      const files = e.target.files;
      const fileReader = new FileReader();
      fileReader.onload = (ev) => {
        const data = ev.target.result;
        const workbook = XLSX.read(data, {
          type: "binary",
        });
        const wsname = workbook.SheetNames[0]; //取第一
        const ws = XLSX.utils.sheet_to_row_object_array(
          workbook.Sheets[wsname]
        );
        this.rows = new Array();
        this.columns = new Array();
        Object.keys(ws[0]).forEach((item) => {
          this.columns.push({ key: item, title: item });
        });
        this.rows = ws;
        this.$refs.upload.value = "";
        this.$emit("column-change", this.columns);
      };
      this.file = files[0];
      fileReader.readAsBinaryString(files[0]);
    },
    getFile() {
      return this.file;
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
