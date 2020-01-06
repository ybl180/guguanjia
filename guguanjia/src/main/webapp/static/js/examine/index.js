new Vue({
    el: "#main-container",
    data: function () {
        return {
            setting: {
                data: {
                    simpleData: {
                        enable: true,//开启简单数据模式支持
                        pIdKey:"parentId"
                    }
                },
                callback: {
                    onClick: this.clickNode//如果设置this.xxx  methods对象还没有，不能绑定上
                },
                view: {
                    fontCss: this.fontCss//每次对元素节点进行创建或修改的时候都会自动调用该样式设置规则
                }
            },
            name: '',
            params: {},
            pageInfo: '',
            officeName:"全部"
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: "manager/examine/toList",
                method: "post",
                data: this.params,
            }).then(response => {
                this.pageInfo = response.data;
            }).catch()
        },

        //初始化菜单树
        initTree: function () {
            axios({
                url: "manager/office/list"
            }).then(response => {
                let nodes = response.data;
                nodes[nodes.length] = {id: 0, name: '所有机构', open: true};
                let treeObj = $.fn.zTree.init($("#treeDemo"), this.setting, nodes);
                console.log(treeObj.getNodes())//复杂数组格式
            }).catch(error => {
                layer.msg(error);
            })

        },
        clickNode: function (event, treeId, treeNode) {
            this.officeName = treeNode.name;
            this.params.examineUserOfficeId=treeNode.id;
        },
        search: function () {
            /**
             * 1.获取树对象
             * 2.进行模糊查询匹配到所有的匹配节点数组
             * 3.获取所有节点数据，转换成简单数组模式
             * 4.遍历所有节点，给所有找到的节点设置一个高亮标记属性  清除前需要修改旧查询到的节点标记为false
             * 5.更新树对象
             */
            let zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
            let nodes = zTreeObj.getNodesByParamFuzzy("name", this.name, null);
            console.log(nodes);
            let zTreeNodes = zTreeObj.transformToArray(zTreeObj.getNodes());
            console.log(zTreeNodes);

            //先清除高亮
            for (let i = 0; i < zTreeNodes.length; i++) {
                zTreeNodes[i].highLight = false;
                zTreeObj.updateNode(zTreeNodes[i]);
            }

            for (let i = 0; i < zTreeNodes.length; i++) {
                for (let j = 0; j < nodes.length; j++) {
                    if (zTreeNodes[i].name == nodes[j].name) {
                        zTreeNodes[i].highLight = true;
                        zTreeObj.updateNode(zTreeNodes[i]);//在树对象上更新节点
                        break;
                    }
                }
            }
        },

        fontCss: function (treeId, treeNode) {
            //如果treeNode是 单位管理，则高亮
            // return treeNode.name=="单位管理"?{color:"red"}:'';
            //如果是highLight标记为true的高亮
            return treeNode.highLight ? {color: "red"} : {color: 'black'};
        }

    },
    created: function () {
        this.selectAll(1, 5);
    }, mounted: function () {//在挂载dom后调用
        this.initTree();
    }
});