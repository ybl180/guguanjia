new Vue({
    el: "#main-container",
    data: {
        user: {
            officeName: ''
        }
    },
    methods: {
        selectOffice: function () {
            axios({
                url: "manager/office/selectByUid",
                params: {uid: this.user.id}
            }).then(response => {
                this.user.officeName = response.data.name;
            }).catch(error => {
                layer.msg(error.message)
            })
        }
    },
    created: function () {
        this.user = parent.layer.user;
        this.selectOffice();
    }
})