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
          <div v-if="item.id == 'register'">
            <register></register>
          </div>
          <div v-if="item.id == 'charge'">
            <charge></charge>
          </div>
          <div v-if="item.id == 'dailySum'">
            <dailySum></dailySum>
          </div>
          <div v-if="item.id == 'dailySumHistory'">
            <dailySumHistory></dailySumHistory>
          </div>
          <!--<router-view name="office"></router-view name="office">-->
        </v-card>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script>
import register from '@/pages/RegisterCharge/register'
import charge from '@/pages/RegisterCharge/charge'
import dailySum from '@/pages/RegisterCharge/dailySum'
import dailySumHistory from '@/pages/RegisterCharge/dailySumHistory'

export default {
  name: 'RegisterCharge',
  components: {
    charge,
    register,
    dailySum,
    dailySumHistory
  },
  data () {
    return {
      active: null,
      items: [{
        name: '门诊挂号',
        id: 'register'
      },
      {
        name: '收费',
        id: 'charge'
      },
      {
        name: '收费员日结',
        id: 'dailySum'
      },
      {
        name: '日结历史查询',
        id: 'dailySumHistory'
      }]
    }
  },
  mounted: function () {
    this.Check()
  },
  methods: {
    Check: function () {
      let that = this
      var storage = window.localStorage
      if (storage.getItem('type') === '挂号收费员') {
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
      } else if (this.$store.state.user.type === '挂号收费员') {
        this.$router.push('/RegisterCharge')
      } else {
        this.$router.push('/login')
      }
    }
  }
}
</script>

<style scoped>

</style>
