new Vue({
    el: "#main-container",
    data: {
        statute: {},
        ueditorConfig: {
            UEDITOR_HOME_URL: 'static/ueditor/',
            serverUrl: 'doExec'          // "ueditor/doExec"
        }
    },
    components: {//引入vue组件
        VueUeditorWrap
    },
    methods: {
        doUpdate: function () {
            axios({
                url: "manager/statute/doUpdate",
                method: 'post',
                data: this.statute,
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
        this.statute = parent.layer.obj;
    }
});