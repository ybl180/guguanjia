new Vue({
    el: "#main-container",
    data: {
        area: {}
    },
    methods: {
        update: function () {
            this.area.oldParentId = this.area.parentId;
            axios({
                url: "manager/area/doUpdate",
                method: "post",
                data: this.area,
            }).then(response => {
                if (response.data.success) {
                    //更新成功
                    this.closeWindow();
                    parent.layer.msg(response.date.msg)
                } else {
                    //失败
                    layer.msg(response.data.msg);
                }
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        closeWindow: function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        },
        selectIcon: function () {
            layer.open({
                type: 2,
                content: "manager/area/awesome",
                area: ["80%", "80%"],
                end: () => {//将then函数中的this传递到end的回调函数中
                    this.area.icon = layer.icon;
                }
            })
        },

        selectParent:function(){
            layer.open({
                type: 2,
                content: "manager/area/selectParent",
                area: ["80%", "80%"],
                end: () => {//将then函数中的this传递到end的回调函数中
                    this.area.parentId = layer.parentId;
                    this.area.parentName = layer.parentName;
                }
            })
        },

    },
    created: function () {
        this.area = parent.layer.obj;
    },mounted:function () {
        //chosen是一个下拉列表的插件:本质就是通过js将原来的下拉列表隐藏，并且额外添加div来实现
        $("#chosen-select").chosen({width:'100%',disable_search:true});//初始化chosen
    }
})