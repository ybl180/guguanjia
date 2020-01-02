new Vue({
    el: "#main-container",
    data: {
        qualification: {},
        offerId: ''
    },
    methods: {
        doUpdate: function (check) {
            this.qualification.check = check;
            axios({
                url: "manager/qualification/doUpdate",
                method: "post",
                data: this.qualification
            }).then(response => {
                parent.layer.close(parent.layer.getFrameIndex(window.name));
                parent.layer.msg(response.data.msg);
            }).catch()
        }

    },
    created: function () {
        this.qualification = parent.layer.obj;
        this.offerId = parent.layer.offerId;
    }

})