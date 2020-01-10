new Vue({
    el: ".main-content",
    data: function () {
        return {
            setting: {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    },
                }, callback: {
                    onClick: this.onClick,
                }, view: {
                    fontCss: this.fontCss
                }
            },
            nodes: [],
            treeObj: '',
            officeName: ''
        }

    },
    methods: {
        initTree: function () {
            axios({
                url: "manager/office/list"
            }).then(response => {
                this.nodes = response.data;
                this.nodes[this.nodes.length] = {id: 0, name: '所有机构', open: true};
                this.treeObj = $.fn.zTree.init($("#select-tree"), this.setting, this.nodes)
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        onClick: function (event, treeId, treeNode) {
            if (treeNode.id != 0) {
                parent.layer.changeOfficeId = treeNode.id;
                parent.layer.changeOfficeName = treeNode.name;
                this.officeName = treeNode.name;
            } else {
                this.officeName = "";
            }
            parent.layer.close(parent.layer.getFrameIndex(window.name))
        },
        search: function () {
            //获取树对象
            let zTreeObj = $.fn.zTree.getZTreeObj("select-tree");
            //获取所有树数据，将他转换成简单数组
            let zTreeNodes = zTreeObj.transformToArray(zTreeObj.getNodes());
            //进行模糊查询匹配到所有匹配到的字节数组
            let nodes = zTreeObj.getNodesByParamFuzzy("name", this.officeName, null);

            //遍历先清除高亮
            for (let i = 0; i < zTreeNodes.length; i++) {
                zTreeNodes[i].hightLight = false;
                //更新树对象
                this.treeObj.updateNode(zTreeNodes[i]);
            }
            for (let i = 0; i < nodes.length; i++) {
                nodes[i].hightLight = true;
                this.treeObj.updateNode(nodes[i]);
                //展开指定的节点
                this.treeObj.expandNode(nodes[i].getParentNode(), true, false, false);
            }
        },
        fontCss: function (treeId, treeNode) {
            return treeNode.hightLight ? {color: "red"} : {color: "black"}
        },
        close: function () {
            parent.layer.close(parent.layer.getFrameIndex(window.name))
        }
    },
    created: function () {
        this.initTree();
        this.officeName = parent.layer.roleOfficeName;
        parent.layer.changeOfficeId = parent.layer.roleOfficeId;
        parent.layer.changeOfficeName = parent.layer.roleOfficeName
    },
});