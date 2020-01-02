new Vue({
    el: "#main-container",
    data: function () {
        return {
            params: {
                status: ''
            },
            pageInfo: '',
            zSetting: {
                data: {
                    simpleData: {
                        enable: true,//开启简单数据模式支持
                        pIdKey: 'parentId'
                    }
                },
                callback: {
                    onClick: this.clickNode
                },
                view: {
                    fontCss: this.fontCss
                }
            },
            officeName: '全部',
            name: '',
            treeObj: ''
        }

    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: "manager/admin/toList",
                method: "post",
                data: this.params
            }).then(response => {
                this.pageInfo = response.data;
            }).catch()
        },
        initTree: function () {
            axios({
                url: "manager/sys_office/list"
            }).then(response => {
                let zNodes = response.data;
                zNodes[zNodes.length] = {id: 0,  name: '所有机构', open: true};
                console.log(zNodes);
                this.treeObj = $.fn.zTree.init($("#pullDownTreeOne"), this.zSetting, zNodes)
            }).catch()

        },
        clickNode: function (event, treeId, treeNode) {
            this.officeName = treeNode.name;
            this.params.officeId = treeNode.id
        },
        search: function () {
            //获取树对象
            let zTreeObj = $.fn.zTree.getZTreeObj("pullDownTreeOne");
            //获取所有树数据，将他转换成简单数组
            let zTreeNodes = zTreeObj.transformToArray(zTreeObj.getNodes());
            //进行模糊查询匹配到所有匹配到的字节数组
            let nodes = zTreeObj.getNodesByParamFuzzy("name", this.name, null);

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

        selectOne: function (oid) {
            axios({
                url:"manager/admin/selectByOid",
                params: {oid:oid}
            }).then(response=>{
                layer.obj=response.data;
                layer.open({
                    type:2,
                    area:["80%","80%"],
                    content:"manager/admin/toDetail"
                })
            }).catch()
        },
        printTable:function(oid){
            axios({
                url:"manager/admin/selectByOid",
                params: {oid:oid}
            }).then(response=>{
                layer.obj=response.data;
                layer.open({
                    type:2,
                    area:["80%","80%"],
                    content:"manager/admin/print"
                })
            }).catch()
        }

    },
    created: function () {
        this.selectAll(1, 5);
    },
    mounted: function () {
        this.initTree();
    }
})