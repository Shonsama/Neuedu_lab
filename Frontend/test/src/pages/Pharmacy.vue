<template>
  <div>
    <v-tabs
      v-model="active"
    >
      <v-tab
        v-for="item in items"
        :key="item"
        ripple
      >
        {{item.name}}
      </v-tab>
      <v-tab-item
        v-for="item in items"
        :key="item"
      >
        <v-card flat>
          <div v-if="item.id == 'medicine'">
            <medicine></medicine>
          </div>
          <div v-if="item.id == 'itemManage'">
            <itemManage></itemManage>
          </div>
          <!--<router-view name="office"></router-view name="office">-->
        </v-card>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script>
import medicine from '@/pages/Pharmacy/medicine'
import itemManage from '@/pages/Pharmacy/itemManage'

export default {
  name: 'Pharmacy',
  components: {
    medicine,
    itemManage
  },
  data () {
    return {
      active: null,
      items: [{
        name: '门诊发药',
        id: 'medicine'
      },
      {
        name: '药品管理',
        id: 'itemManage'
      }]
    }
  },
  created: function () {
    this.Check()
  },
  methods: {
    Check: function () {
      let that = this
      var storage = window.localStorage
      if (storage.getItem('type') === '药房操作员') {
        var data = {
          account: storage.getItem('account'),
          department_id: storage.getItem('department_id'),
          id: storage.getItem('id'),
          name: storage.getItem('name'),
          type: storage.getItem('type')
        }
        that.$store.commit('set_user', data)
        that.$store.commit('login')
      }
      if (!this.$store.state.isLogin) {
        this.$router.push('/login')
      } else if (this.$store.state.user.type === '药房操作员') {
        this.$router.push('/Pharmacy')
      } else {
        this.$router.push('/login')
      }
    }
  }
}
</script>

<style scoped>

</style>
