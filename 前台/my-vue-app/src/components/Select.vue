<template>
  <div>
    <div class="block">
      <div class="select">选择数量</div>
      <el-cascader
          v-model="value1"
          :options="options1"
          @change="handleChange"></el-cascader>
    </div>
    <div class="block">
      <div class="select">选择类型</div>
      <el-cascader
          v-model="value2"
          :options="options2"
          @change="handleChange"></el-cascader>
    </div>
    <div class="button_box">
      <el-button type="primary" @click="search">查询</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value1: [],
      value2: [],
      options1: [{
        value: '30',
        label: '30',
      }, {
        value: '40',
        label: '40',
      },
        {
          value: '50',
          label: '50',
        },
        {
          value: '60',
          label: '60',
        },
        {
          value: '70',
          label: '70',
        },
        {
          value: '80',
          label: '80',
        },
        {
          value: '90',
          label: '90',
        },
        {
          value: '100',
          label: '100',
        },
      ],
      //2022年真题    加分宝-0频词    加分宝-超纲词    加分宝-简单词  1频词     低频  中频   高频
      options2: [{
        value: '所有单词',
        label: '所有单词',
      },{
        value: '高频',
        label: '高频',
      }, {
        value: '中频',
        label: '中频',
      },
        {
          value: '低频',
          label: '低频',
        },
        {
          value: '1',
          label: '1频词',
        },
        {
          value: '简',
          label: '简单词',
        },
        {
          value: '超',
          label: '超纲词',
        },
        {
          value: '0',
          label: '0频词',
        },
        {
          value: '真',
          label: '2022年真题',
        },
      ]
    };
  },
  watch: {
    value1(newVal) {
      console.log(newVal)
      this.$store.commit('updateCount', newVal[0])
    },
    value2(newVal) {
      console.log(newVal)
      this.$store.commit('updateType', newVal[0])
    }
  },
  methods: {
    search() {
      this.$store.commit('search', true);
    },
    handleChange(value) {
      //console.log(value);
    },
  }
};
</script>

<style>
.el-input__icon, .is-reverse {
  transform: translateX(100px) !important;
}

.el-icon-arrow-down {
  transform: translateX(100px) !important;
}

.block {
  margin-top: 10px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.select {
  font-family: '华文楷体';
  font-weight: 600;
}

.button_box {
  margin-top: 20px;
  text-align: center;
}
</style>
