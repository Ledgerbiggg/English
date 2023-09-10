<template>
<div>
<div class="upload-box">
  <el-upload
      class="upload-demo"
      drag
      action=""
      accept=".xls,.xlsx"
      :on-change="handleChange"
      :http-request="uploadFile"
  >
    <i class="el-icon-upload"></i>
    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    <div class="el-upload__tip" slot="tip">只能上传xls/xlsx文件，且不超过500kb</div>
  </el-upload>
  <el-button type="primary" @click="onSubmit">上传表格</el-button>
</div>
</div>

</template>

<script>
import axios from "axios";

export default {
  name: "Upload",
  data(){
    return {
      fileList: [],
      headers: { "Content-Type": "multipart/form-data" },
    }
  },
  methods:{
    onSubmit(){
      console.log(this.fileList,"fileList")
      this.$message.info('文件上传中........')
      //上传文件的需要formdata类型;所以要转
      let formData = new FormData()
      this.fileList.forEach(i=>{
        formData.append('file',i.raw);
      })
      axios.post("http://ledger-code.buzz:9999/saveAllExcelList",formData,{
        headers: this.headers,
      }).then(res=>{
        console.log(res);
        this.$message.success('文件上传成功')
      })
    },
    handleChange (file, fileList) {
      this.fileList.push(file)
      console.log(this.fileList,"file")
    },
    uploadFile(){

    }


  }
}
</script>

<style scoped>
.upload-box{
  margin-top: 100px;
  text-align: center;
}
</style>
