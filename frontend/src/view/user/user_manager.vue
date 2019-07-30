<template>
  <div>
    <Card>
      <Button style="margin: 10px 0;" type="primary" @click="openAddUserModal">新增用户</Button>
      <tables ref="tables" editable searchable search-place="top" v-model="tableData" :columns="columns"/>
      <div style="margin: 10px;overflow: hidden">
        <div style="float: right;">
            <Page :total="datacount" :current="1" @on-change="changePage"></Page>
        </div>
      </div>
    </Card>
  <Modal
    title="Title"
    v-model="addUserModal"
    @on-ok="submitUser"
    :loading=loading
    :closable="false">
    <Form :model:="formItem" :label-width="80">
      <FormItem label="用户名">
        <Input v-model="formItem.name" placeholder="用户名"/>
      </FormItem>
      <FormItem label="密码">
        <Input v-model="formItem.password" placeholder="密码"/>
      </FormItem>
      <FormItem label="权限">
        <Select v-model="formItem.privilege" style="width:200px">
          <Option v-for="item in privileges" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </FormItem>
    </Form>
  </Modal>

  </div>
</template>

<script>
import Tables from '_c/tables'
// import { getSiteCount, getSiteList } from '@/api/site'
import { getUserList, getUserCount, addUser, listPrivileges } from '@/api/user'
export default {
  name: 'tables_page',
  components: {
    Tables
  },
  data () {
    return {

      loading: true,
      formItem: {
        name: '',
        password: '',
        privilege: ''
      },
      privileges: [],
      addUserModal: false,
      datacount: 0,
      currentPage: 1,
      columns: [
        {title: '用户名', key: 'name'},
        {title: '权限', key: 'access'},
        {title: '启用', key: 'enable', render: (h, params) => {
            const row = params.row;
            const text =  row.enable ? 'Y' : 'N';
            const color = row.enable ? 'success' : 'error';

            return h('Tag', {
                props: {
                    type: 'dot',
                    color: color
                }
            }, text);
        }},
      ],
      tableData: [],

    }
  },
  methods: {

    changePage (page) {
      this.currentPage = page
      getUserList((page - 1) * 10, 10).then(res => {
        this.tableData = res.data.data
      })
    },

    openAddUserModal () {
      this.addUserModal = true
    },

    submitUser() {
      addUser(this.formItem.name, this.formItem.password, this.formItem.privilege).then(res => {
        const data = res.data
        if (data.code === 200) {
          this.addUserModal = false 
          this.$Notice.success({
            title: '用户创建成功',
            desc: '成功'
          });
        } else {
          this.$Notice.error({
            title: '用户创建失败',
            desc: data.msg
          });
          this.loading = false
        }
      }).catch(error => {
        this.$Notice.error({
          title: '用户创建失败',
          desc: error
        });
        this.loading = false
      })
    }

    // changeLoading() {
    //   this.loading = false
    //   this.$nextTick(() => {
    //     this.loading = true
    //   })
    // }

    // ok () {
    //   this.$refs['form'].validate(valid => {
    //     if (!valid) {
    //       return this.changeLoading();
    //     }
    //   })
    // }
  },
  mounted () {
    getUserCount().then(res => {
      this.datacount = res.data.data
    })
    this.changePage(this.currentPage)

    listPrivileges().then(res => {
      res.data.data.forEach((privilege) => {
        console.log(privilege)
        this.privileges.push({
          value: privilege,
          label: privilege
        })
      })
    })
  }
}
</script>

<style>

</style>

