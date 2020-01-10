new Vue({
    el: "#main-container",
    data: {
        role: {},
        users: {},
        resources: {}
    },
    methods: {
        selectUser: function () {
            axios({
                url: "manager/sysuser/selectByRid",
                params: {rid: this.role.id}
            }).then(response => {
                this.users = response.data
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        selectResource: function () {
            axios({
                url: "manager/menu/selectByRid",
                params: {rid: this.role.id}
            }).then(response => {
                this.resources = response.data;
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        detailUser:function (user) {
            layer.user=user;
            layer.open({
                type:2,
                content:'manager/sysuser/detail',
                area:["80%","80%"],
                title:"用户详情"
            })
        }
    },
    created: function () {
        this.role = parent.layer.obj;
        this.selectUser();
        this.selectResource();
    }

})