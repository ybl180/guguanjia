new Vue({
    el: "#main-container",
    data: function () {
        return {
            pageInfo: '',
            params: {
                name: "",
                pid: 0,
            },

            setting: {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    }
                },
                callback: {
                    onClick: this.onClick,
                    beforeEditName: this.beforeEditName
                },
                edit: {
                    enable: true
                },
                view:{//自定义节点上的元素
                    addHoverDom:this.addHoverDom,
                    removeHoverDom:this.removeHoverDom
                }

            },
            nodes: [],
            treeObj: {},
        }

    },
    methods: {
        download: function () {
            location.href = 'manager/area/download';
        },
        upload: function (event) {
            console.log(event.target.files[0]);
            let formData = new FormData;//构建表单对象
            formData.append("upFile", event.target.files[0]);//upFile需要与后台接收MultipartFile的名字一致
            axios({
                url: "manager/area/upload",
                data: formData,
                method: "post",
                headers: {//设置请求头
                    'Content-Type': 'Multipart/form-data'//设置请求题格式
                }
            }).then(response => {
                layer.msg(response.data.msg);
            }).catch(error => {
                layer.msg(error.message);
            });
        },


        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: "manager/area/toList",
                method: "post",
                data: this.params,
            }).then(response => {
                this.pageInfo = response.data;
                console.log(this.pageInfo)
            }).catch(error => {
                layer.msg(error.message);
            })
        },
        all: function () {
            this.params.name = "";
            this.selectAll(1, 5)
        },
        initTree: function () {
            axios({
                url: "manager/area/selectAll"
            }).then(response => {
                this.nodes = response.data;
                this.treeObj = $.fn.zTree.init($("#treeMenu"), this.setting, this.nodes)
            }).catch(error => layer.msg(error.message))
        },

        onClick: function (event, treeId, treeNode) {
            this.params.name = "";
            this.params.pid = treeNode.id;
            // this.params.name = treeNode.name;
            this.selectAll(1, 5);
        },

        toUpdate: function (id) {
            axios({
                url: "manager/area/toUpdate",
                params: {id: id},
            }).then(response => {
                layer.obj = response.data;
                layer.open({
                    type: 2,
                    content: "manager/area/toUpdatePage",
                    area: ["80%", "80%"],
                    end: () => {
                        //刷新页面
                        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
                        //刷新菜单树
                        this.initTree();
                    }
                })
            }).catch(error => layer.msg(error.message))

        },
        beforeEditName: function (treeId, treeNode) {//结合开启修改节点按钮、点击修改按钮事件回调处理更新节点弹框
            this.toUpdate(treeNode.id);
            return false;//阻止进入修改节点状态
        },
        addHoverDom:function () {
            var aObj = $("#" + treeNode.tId + "_a");
            if ($("#diyBtn_"+treeNode.id).length>0) return;
            var editStr = "<span id='diyBtn_space_" +treeNode.id+ "' > </span>"
                + "<button type='button' class='diyBtn1' id='diyBtn_" + treeNode.id
                + "' title='"+treeNode.name+"' onfocus='this.blur();'></button>";
            aObj.append(editStr);
            var btn = $("#diyBtn_"+treeNode.id);
            if (btn) btn.bind("click", function(){alert("diy Button for " + treeNode.name);});
        },
        removeHoverDom:function () {
            $("#diyBtn_"+treeNode.id).unbind().remove();
            $("#diyBtn_space_" +treeNode.id).unbind().remove();
        }
        

    },
    created: function () {
        this.selectAll(1, 5);
    },
    mounted: function () {
        this.initTree();
    }
})
;