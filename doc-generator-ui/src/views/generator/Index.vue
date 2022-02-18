<template>
  <div>
    <data-setting
      ref="dataSetting"
      :table-columns="dataColumns"
      :table-rows="dataRows"
      @column-change="handleDataColumnChange"
    ></data-setting>
    <template-setting
      ref="templateSetting"
      :table-columns="templateColumns"
      :table-rows="templateRows"
    ></template-setting>
    <div class="step3">
      <el-button type="success" @click="handleGenerator">
        智能生成文档
      </el-button>
    </div>
  </div>
</template>

<script>
import DataSetting from "./DataSetting";
import TemplateSetting from "./TemplateSetting";
import { generator } from "@/api/generator";

export default {
  name: "Generator",
  components: {
    DataSetting,
    TemplateSetting,
  },
  data() {
    return {
      dataColumns: [],
      dataRows: [],
      templateColumns: [],
      templateRows: [],
    };
  },
  methods: {
    handleGenerator() {
      const excelFile = this.$refs.dataSetting.getFile();
      const docFiles = this.$refs.templateSetting.getFiles();
      generator(excelFile, docFiles)
        .then((result) => {
          console.log(result);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleDataColumnChange(columns) {
      console.log(this.dataColumns);
      this.templateColumns = columns;
      // this.templateColumns = new Array();
      // columns.forEach((column, index) => {
      //   this.$set(this.templateColumns, index, column);
      // });
    },
  },
};
</script>

<style>
.box-card {
  margin-bottom: 0.5rem;
}
.step3 {
  text-align: center;
}
</style>
