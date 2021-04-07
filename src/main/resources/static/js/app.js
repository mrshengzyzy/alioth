/**
 * 全局变量
 */
const DefaultPageSize = 7;

/**
 * 分页插件
 * 修改自 https://github.com/thinksea/bootstrap-pagination
 */
var BootstrapPagination = function (obj, option) {
    var innerBootstrapPagination = function (obj, option) {
        /**
         * 判断用户端访问设备是否手机。
         * @returns {Boolean} 如果是手机则返回 true；否则返回 false。
         */
        var isMobile = function () {
            if (isMobile._isMobile === null) {
                var a = navigator.userAgent || navigator.vendor || window.opera;
                if (/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(a) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0, 4))) {
                    isMobile._isMobile = true;
                } else {
                    isMobile._isMobile = false;
                }
            }
            return isMobile._isMobile;
        }
        isMobile._isMobile = null; //用于缓冲结果，避免冗余计算过程。

        // 参数设置。
        this.options = {
            //记录总数。
            total: 0,
            //分页尺寸。指示每页最多显示的记录数量。
            pageSize: 20,
            //当前页索引编号。从其开始（从0开始）的整数。
            pageIndex: 0,
            //指示分页导航栏中最多显示的页索引数量。
            pageGroupSize: 10,
            //位于导航条左侧的输出信息格式化字符串
            leftFormateString: "本页{count}条记录/共{total}条记录",
            //位于导航条右侧的输出信息格式化字符串
            rightFormateString: "第{pageNumber}页/共{totalPages}页",
            //页码文本格式化字符串。
            pageNumberFormateString: "{pageNumber}",
            //分页尺寸输出格式化字符串
            pageSizeListFormateString: "每页显示{pageSize}条记录",
            //上一页导航按钮文本。
            prevPageText: "上一页",
            //下一页导航按钮文本。
            nextPageText: "下一页",
            //上一组分页导航按钮文本。
            prevGroupPageText: "上一组",
            //下一组分页导航按钮文本。
            nextGroupPageText: "下一组",
            //首页导航按钮文本。
            firstPageText: "首页",
            //尾页导航按钮文本。
            lastPageText: "尾页",
            //设置页码输入框中显示的提示文本。
            pageInputPlaceholder: "GO",
            //接受用户输入内容的延迟时间。单位：毫秒
            pageInputTimeout: 800,
            //分页尺寸列表。
            pageSizeList: [5, 10, 20, 50, 100, 200],
            //当分页更改后引发此事件。
            pageChanged: function (pageIndex, pageSize) {
            },
            //布局方案。指示按照什么样的排列顺序显示哪些元素。
            layoutScheme: "lefttext,pagesizelist,firstpage,prevgrouppage,prevpage,pagenumber,nextpage,nextgrouppage,lastpage,pageinput,righttext",
        };

        // 获取或设置分页索引。
        this.pageIndex = function (newPageIndex) {
            if (newPageIndex === undefined) {
                return this.options.pageIndex;
            } else {
                this.options.pageIndex = newPageIndex;
                this.fixPageIndex();
                this.render();
                if (this.options.pageChanged) {
                    this.options.pageChanged(this.options.pageIndex, this.options.pageSize);
                }
            }
        }

        // 获取或设置分页尺寸。
        this.pageSize = function (newPageSize) {
            if (newPageSize === undefined) {
                return this.options.pageSize;
            } else {
                this.options.pageSize = newPageSize;
                this.fixPageIndex();
                this.render();
                if (this.options.pageChanged) {
                    this.options.pageChanged(this.options.pageIndex, this.options.pageSize);
                }
            }
        }

        // 获取分页总数。
        this.totalPages = function () {
            return Math.floor((this.options.total + this.options.pageSize - 1) / this.options.pageSize);
        }

        // 获取当前页实际显示的记录数量。
        this.currentCount = function () {
            var rCount = this.options.total - this.options.pageSize * this.options.pageIndex;
            if (rCount > this.options.pageSize) {
                return this.options.pageSize;
            } else {
                return rCount;
            }
        }

        // 创建分页按钮。
        this.createPageButton = function (text, pageNum) {
            var li = $("<li class='page-item'></li>");
            var a = $("<a class='page-link' href='javascript:;'>" + this.options.pageNumberFormateString.replace("{pageNumber}", text) + "</a>");
            if (pageNum !== undefined && pageNum != this.options.pageIndex) {
                a.off('click').on('click', $.proxy(this.pageIndex, this, pageNum));
            }
            li.append(a);
            if (pageNum !== undefined && pageNum == this.options.pageIndex) {
                li.addClass("active");
            }
            if (pageNum === undefined) {
                li.addClass("disabled");
            }
            return li;
        }

        // 创建文本标签。
        this.createLabel = function (text) {
            var li = $('<li><span>' + text + '</span></li>');
            li.addClass("disabled");
            return li;
        }

        // 执行格式化字符串。
        this.doFormateString = function (formateString) {
            return formateString.replace("{count}", this.currentCount())
                .replace("{total}", this.options.total)
                .replace("{pageNumber}", this.options.total > 0 ? this.options.pageIndex + 1 : 0)
                .replace("{totalPages}", this.totalPages());
        }

        // 创建分页尺寸列表控件。
        this.createPageSizeList = function (align) {
            var li = $('<li></li>');
            var el1 = $('<div class="input-group dropup" style="float: left;"></div>');
            var el2 = $('<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="true">' +
                this.options.pageSizeListFormateString.replace("{pageSize}", '<span class="pagesize">' + this.options.pageSize + '</span>') +
                '<span class="caret"></span></button>');
            switch (align) {
                case 1:
                    el2.removeClass("pagesize-fix-center").removeClass("pagesize-fix-right").addClass("pagesize-fix-left");
                    break;
                case 2:
                    el2.removeClass("pagesize-fix-left").removeClass("pagesize-fix-right").addClass("pagesize-fix-center");
                    break;
                case 3:
                    el2.removeClass("pagesize-fix-left").removeClass("pagesize-fix-center").addClass("pagesize-fix-right");
                    break;
            }
            var el3 = $('<ul class="dropdown-menu" role="menu"></ul>');
            li.append(el1);
            el1.append(el2);
            el1.append(el3);
            for (var i = 0; i < this.options.pageSizeList.length; i++) {
                var tmp = this.options.pageSizeList[i];
                var liItem = $('<li role="presentation"></li>');
                if (tmp == this.options.pageSize) {
                    liItem.attr("class", "active");
                }
                var a = $('<a role="menuitem" tabindex="-1" href="javascript:;">' + tmp + '</a>');
                a.off('click').on('click', $.proxy(this.pageSize, this, tmp));
                liItem.append(a);
                el3.append(liItem);
            }
            return li;
        }

        // 创建页码输入框。
        this.createPageInput = function () {
            var li = $('<li></li>');
            var inputgroup = $('<div class="input-group page-input"></div>');
            li.append(inputgroup);
            var inputType = "text";
            if (isMobile()) {
                inputType = "number";
            }
            var input = $('<input type="' + inputType + '" class="form-control" />');
            if (this.options.pageInputPlaceholder) {
                input.attr("placeholder", this.options.pageInputPlaceholder);
            }
            inputgroup.append(input);
            input.off('keyup').on('keyup', $.proxy(this.pageInputEvents, this));
            return li;
        }

        // 延迟计时器ID，这个延迟计时器用于延迟执行用户输入跳转页码的工作。
        this.timeoutId = 0;
        // 处理输入页码事件。
        this.pageInputEvents = function (event) {
            var ctl = $(event.target);
            var sNum = ctl.val();
            var reg = /^\d+$/gi;
            if (!reg.test(sNum)) {
                //alert("输入的页码格式无效。");
                ctl.parent().addClass("has-error");
                return false;
            }
            ctl.parent().removeClass("has-error");
            var pageNum = parseInt(sNum) - 1;
            var that = this;
            clearTimeout(this.timeoutId);
            this.timeoutId = setTimeout(function () {
                that.pageIndex(pageNum);
            }, this.options.pageInputTimeout);
        }

        // 调整页码索引。
        this.fixPageIndex = function () {
            {
                var tp = this.totalPages();
                if (this.options.pageIndex > tp - 1) {
                    this.options.pageIndex = tp - 1;
                }
                if (this.options.pageIndex < 0) {
                    this.options.pageIndex = 0;
                }
            }
        }

        // 呈现控件。
        this.render = function () {
            var lis = [];

            var layoutItems = this.options.layoutScheme.split(",");
            for (var i_layout = 0; i_layout < layoutItems.length; i_layout++) {
                switch (layoutItems[i_layout]) {
                    case "lefttext":
                        //#region 处理左侧输出信息格式化字符串
                        if (this.options.leftFormateString) {
                            lis[lis.length] = this.createLabel(this.doFormateString(this.options.leftFormateString));
                        }
                        //#endregion
                        break;
                    case "firstpage":
                        //#region 首页按钮
                        if (this.options.firstPageText) {
                            if (this.options.pageIndex == 0) {
                                lis[lis.length] = this.createPageButton(this.options.firstPageText);
                            } else {
                                var pageNum = 0;
                                lis[lis.length] = this.createPageButton(this.options.firstPageText, pageNum);
                            }
                        }
                        //#endregion
                        break;
                    case "prevgrouppage":
                        //#region 上一组分页按钮
                        if (this.options.prevGroupPageText) {
                            if (this.options.pageIndex == 0) {
                                lis[lis.length] = this.createPageButton(this.options.prevGroupPageText);
                            } else {
                                var pageNum = (this.options.pageIndex - this.options.pageGroupSize < 0) ? 0 : this.options.pageIndex - this.options.pageGroupSize;
                                lis[lis.length] = this.createPageButton(this.options.prevGroupPageText, pageNum);
                            }
                        }
                        //#endregion
                        break;
                    case "prevpage":
                        //#region 上一页按钮
                        if (this.options.prevPageText) {
                            if (this.options.pageIndex <= 0) {
                                lis[lis.length] = this.createPageButton(this.options.prevPageText);
                            } else {
                                var pageNum = this.options.pageIndex - 1;
                                lis[lis.length] = this.createPageButton(this.options.prevPageText, pageNum);
                            }
                        }
                        //#endregion
                        break;
                    case "pagenumber":
                        //#region 页索引
                        if (this.options.pageNumberFormateString) {
                            var pageNum = this.options.pageIndex - Math.floor((this.options.pageGroupSize - 1) / 2); //分页页码。
                            if (pageNum + this.options.pageGroupSize > this.totalPages() - 1) {
                                pageNum = this.totalPages() - this.options.pageGroupSize;
                            }
                            if (pageNum < 0) {
                                pageNum = 0;
                            }
                            for (var i = 0; i < this.options.pageGroupSize && pageNum < this.totalPages(); i++) {
                                lis[lis.length] = this.createPageButton(pageNum + 1, pageNum);
                                pageNum++;
                            }
                        }
                        //#endregion
                        break;
                    case "nextpage":
                        //#region 下一页按钮
                        if (this.options.nextPageText) {
                            if (this.options.pageIndex < this.totalPages() - 1) {
                                var pageNum = this.options.pageIndex + 1;
                                lis[lis.length] = this.createPageButton(this.options.nextPageText, pageNum);
                            } else {
                                lis[lis.length] = this.createPageButton(this.options.nextPageText);
                            }
                        }
                        //#endregion
                        break;
                    case "nextgrouppage":
                        //#region 下一组分页按钮
                        if (this.options.nextGroupPageText) {
                            if (this.options.pageIndex < this.totalPages() - 1) {
                                var pageNum = (this.options.pageIndex + this.options.pageGroupSize > this.totalPages() - 1) ? this.totalPages() - 1 : this.options.pageIndex + this.options.pageGroupSize;
                                lis[lis.length] = this.createPageButton(this.options.nextGroupPageText, pageNum);
                            } else {
                                lis[lis.length] = this.createPageButton(this.options.nextGroupPageText);
                            }
                        }
                        //#endregion
                        break;
                    case "lastpage":
                        //#region 尾页按钮
                        if (this.options.lastPageText) {
                            if (this.options.pageIndex < this.totalPages() - 1) {
                                var pageNum = this.totalPages() - 1;
                                lis[lis.length] = this.createPageButton(this.options.lastPageText, pageNum);
                            } else {
                                lis[lis.length] = this.createPageButton(this.options.lastPageText);
                            }
                        }
                        //#endregion
                        break;
                    case "pageinput":
                        //#region 处理页码输入框
                        lis[lis.length] = this.createPageInput();
                        //#endregion
                        break;
                    case "pagesizelist":
                        //#region 处理分页尺寸列表控件
                        if (this.options.pageSizeList) {
                            var align = 0;
                            if (layoutItems.length > 1) {
                                if (i_layout > 0 && i_layout < layoutItems.length - 1) { //在中间
                                    align = 2;
                                } else if (i_layout == 0) { //在左侧
                                    align = 1;
                                } else if (i_layout == layoutItems.length - 1) { //在右侧
                                    align = 3;
                                }
                            }
                            lis[lis.length] = this.createPageSizeList(align);
                        }
                        //#endregion
                        break;
                    case "righttext":
                        //#region 处理右侧输出信息格式化字符串
                        if (this.options.rightFormateString) {
                            lis[lis.length] = this.createLabel(this.doFormateString(this.options.rightFormateString));
                        }
                    //#endregion
                }
            }

            obj.children().remove();
            obj.append("<ul class='pagination pagination-sm'></ul>")
            obj.find("ul").append(lis)
        }

        // 初始化。
        this.init = function () {
            //obj = $(obj);
            //#region 根据 HTML 标签上的 data- 属性初始化参数。
            if (obj.data("layoutscheme") !== undefined)
                this.options.layoutScheme = obj.data("layoutscheme");
            if (obj.data("total") !== undefined)
                this.options.total = parseInt(obj.data("total"));
            if (obj.data("pagesize") !== undefined)
                this.options.pageSize = parseInt(obj.data("pagesize"));
            if (obj.data("pagegroupsize") !== undefined)
                this.options.pageGroupSize = parseInt(obj.data("pagegroupsize"));
            if (obj.data("pageindex") !== undefined)
                this.options.pageIndex = parseInt(obj.data("pageindex"));
            if (obj.data("leftformatestring") !== undefined)
                this.options.leftFormateString = obj.data("leftformatestring");
            if (obj.data("rightformatestring") !== undefined)
                this.options.rightFormateString = obj.data("rightformatestring");
            if (obj.data("pagenumberformatestring") !== undefined)
                this.options.pageNumberFormateString = obj.data("pagenumberformatestring");
            if (obj.data("pagesizelistformatestring") !== undefined)
                this.options.pageSizeListFormateString = obj.data("pagesizelistformatestring");
            if (obj.data("prevpagetext") !== undefined)
                this.options.prevPageText = obj.data("prevpagetext");
            if (obj.data("nextpagetext") !== undefined)
                this.options.nextPageText = obj.data("nextpagetext");
            if (obj.data("prevgrouppagetext") !== undefined)
                this.options.prevGroupPageText = obj.data("prevgrouppagetext");
            if (obj.data("nextgrouppagetext") !== undefined)
                this.options.nextGroupPageText = obj.data("nextgrouppagetext");
            if (obj.data("firstpagetext") !== undefined)
                this.options.firstPageText = obj.data("firstpagetext");
            if (obj.data("lastpagetext") !== undefined)
                this.options.lastPageText = obj.data("lastpagetext");
            if (obj.data("pageinput-placeholder") !== undefined)
                this.options.pageInputPlaceholder = obj.data("pageinput-placeholder");
            if (obj.data("pageinput-timeout") !== undefined)
                this.options.pageInputTimeout = parseInt(obj.data("pageinput-timeout"));
            if (obj.data("pagesizelist") !== undefined) {
                this.options.pageSizeList = eval(obj.data("pagesizelist"));
            }
            if (obj.data("pagechanged") !== undefined) {
                var attrPageChanged = obj.data("pagechanged");
                if (typeof (attrPageChanged) == "function") {
                    this.options.pageChanged = attrPageChanged;
                } else if (attrPageChanged.trim().substr(0, 8) == "function") {
                    this.options.pageChanged = function (pageIndex, pageSize) {
                        eval("var fn = " + attrPageChanged);
                        fn(pageIndex, pageSize);
                    };
                } else {
                    this.options.pageChanged = function (pageIndex, pageSize) {
                        eval(attrPageChanged);
                    };
                }
            }
            //#endregion

            if (option !== undefined) {
                $.extend(true, this.options, option); //合并由用户代码设置的参数
                if (option.pageSizeList) {
                    this.options.pageSizeList = option.pageSizeList;
                }
            }

            this.fixPageIndex();
        }

        this.init();
        this.render();
    }

    return new innerBootstrapPagination(obj, option);
}

/**
 * garden.js
 */
function Vector(x, y) {
    this.x = x;
    this.y = y;
};

Vector.prototype = {
    rotate: function (theta) {
        var x = this.x;
        var y = this.y;
        this.x = Math.cos(theta) * x - Math.sin(theta) * y;
        this.y = Math.sin(theta) * x + Math.cos(theta) * y;
        return this;
    },
    mult: function (f) {
        this.x *= f;
        this.y *= f;
        return this;
    },
    clone: function () {
        return new Vector(this.x, this.y);
    },
    length: function () {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    },
    subtract: function (v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    },
    set: function (x, y) {
        this.x = x;
        this.y = y;
        return this;
    }
};

function Petal(stretchA, stretchB, startAngle, angle, growFactor, bloom) {
    this.stretchA = stretchA;
    this.stretchB = stretchB;
    this.startAngle = startAngle;
    this.angle = angle;
    this.bloom = bloom;
    this.growFactor = growFactor;
    this.r = 1;
    this.isfinished = false;
}

Petal.prototype = {
    draw: function () {
        var ctx = this.bloom.garden.ctx;
        var v1, v2, v3, v4;
        v1 = new Vector(0, this.r).rotate(Garden.degrad(this.startAngle));
        v2 = v1.clone().rotate(Garden.degrad(this.angle));
        v3 = v1.clone().mult(this.stretchA);
        v4 = v2.clone().mult(this.stretchB);
        ctx.strokeStyle = this.bloom.c;
        ctx.beginPath();
        ctx.moveTo(v1.x, v1.y);
        ctx.bezierCurveTo(v3.x, v3.y, v4.x, v4.y, v2.x, v2.y);
        ctx.stroke();
    },
    render: function () {
        if (this.r <= this.bloom.r) {
            this.r += this.growFactor;
            this.draw();
        } else {
            this.isfinished = true;
        }
    }
}

function Bloom(p, r, c, pc, garden) {
    this.p = p;
    this.r = r;
    this.c = c;
    this.pc = pc;
    this.petals = [];
    this.garden = garden;
    this.init();
    this.garden.addBloom(this);
}

Bloom.prototype = {
    draw: function () {
        var p, isfinished = true;
        this.garden.ctx.save();
        this.garden.ctx.translate(this.p.x, this.p.y);
        for (var i = 0; i < this.petals.length; i++) {
            p = this.petals[i];
            p.render();
            isfinished *= p.isfinished;
        }
        this.garden.ctx.restore();
        if (isfinished === true) {
            this.garden.removeBloom(this);
        }
    },
    init: function () {
        var angle = 360 / this.pc;
        var startAngle = Garden.randomInt(0, 90);
        for (var i = 0; i < this.pc; i++) {
            this.petals.push(new Petal(Garden.random(Garden.options.petalStretch.min, Garden.options.petalStretch.max), Garden.random(Garden.options.petalStretch.min, Garden.options.petalStretch.max), startAngle + i * angle, angle, Garden.random(Garden.options.growFactor.min, Garden.options.growFactor.max), this));
        }
    }
}

function Garden(ctx, element) {
    this.blooms = [];
    this.element = element;
    this.ctx = ctx;
}

Garden.prototype = {
    render: function () {
        for (var i = 0; i < this.blooms.length; i++) {
            this.blooms[i].draw();
        }
    },
    addBloom: function (b) {
        this.blooms.push(b);
    },
    removeBloom: function (b) {
        var bloom;
        for (var i = 0; i < this.blooms.length; i++) {
            bloom = this.blooms[i];
            if (bloom === b) {
                this.blooms.splice(i, 1);
                return this;
            }
        }
    },
    createRandomBloom: function (x, y) {
        this.createBloom(x, y, Garden.randomInt(Garden.options.bloomRadius.min, Garden.options.bloomRadius.max), Garden.randomrgba(Garden.options.color.rmin, Garden.options.color.rmax, Garden.options.color.gmin, Garden.options.color.gmax, Garden.options.color.bmin, Garden.options.color.bmax, Garden.options.color.opacity), Garden.randomInt(Garden.options.petalCount.min, Garden.options.petalCount.max));
    },
    createBloom: function (x, y, r, c, pc) {
        new Bloom(new Vector(x, y), r, c, pc, this);
    },
    clear: function () {
        this.blooms = [];
        this.ctx.clearRect(0, 0, this.element.width, this.element.height);
    }
}

Garden.options = {
    petalCount: {
        min: 8,
        max: 15
    },
    petalStretch: {
        min: 0.1,
        max: 3
    },
    growFactor: {
        min: 0.1,
        max: 1
    },
    bloomRadius: {
        min: 8,
        max: 10
    },
    density: 10,
    growSpeed: 1000 / 60,
    color: {
        rmin: 125,
        rmax: 255,
        gmin: 0,
        gmax: 128,
        bmin: 0,
        bmax: 128,
        opacity: 0.1
    },
    tanAngle: 60
};
Garden.random = function (min, max) {
    return Math.random() * (max - min) + min;
};
Garden.randomInt = function (min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
};
Garden.circle = 2 * Math.PI;
Garden.degrad = function (angle) {
    return Garden.circle / 360 * angle;
};
Garden.raddeg = function (angle) {
    return angle / Garden.circle * 360;
};
Garden.rgba = function (r, g, b, a) {
    return 'rgba(' + r + ',' + g + ',' + b + ',' + a + ')';
};
Garden.randomrgba = function (rmin, rmax, gmin, gmax, bmin, bmax, a) {
    var r = Math.round(Garden.random(rmin, rmax));
    var g = Math.round(Garden.random(gmin, gmax));
    var b = Math.round(Garden.random(bmin, bmax));
    var limit = 5;
    if (Math.abs(r - g) <= limit && Math.abs(g - b) <= limit && Math.abs(b - r) <= limit) {
        return Garden.rgba(rmin, rmax, gmin, gmax, bmin, bmax, a);
    } else {
        return Garden.rgba(r, g, b, a);
    }
};
