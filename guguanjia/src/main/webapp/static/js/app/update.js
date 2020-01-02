new Vue({
    el: ".main-container",
    data: {
        app: {}
    },
    methods: {
        doUpdate: function () {
            axios({
                url: "manager/app/doUpdate",
                method: 'post',
                data: this.app,
            }).then(response => {
                if (response.data.success) {//更新成功
                    this.closeWindow();
                    parent.layer.msg(response.data.msg);
                    return;
                }
                layer.msg(response.data.msg);
            }).catch(error => {
                console.log(error)
            })
        },
        closeWindow: function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    },
    created: function () {
        this.app = parent.layer.obj;
    }
});