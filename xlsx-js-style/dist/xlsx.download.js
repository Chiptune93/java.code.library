var xlsx_ = {};
$(document).ready(function () {
    if (xlsx_ == null) xlsx_ = {};
    xlsx_ = $.extend(xlsx_, {
        download: function (tableId, fileName, excelTitle, sheetTitle, exceptColumn) {
            // Create WorkBook
            var wb = XLSX.utils.book_new();
            // Define Option
            var opt = {
                // new row except table element
                rowIndex: 1,
                // merge option ( if you have to merge new row )
                exceptColumn: exceptColumn ? exceptColumn : [],
                merges: [{
                    // start
                    s: {
                        c: 0, // col
                        r: 0
                        // row
                    },
                    // end
                    e: {
                        c: ($("#" + tableId).find("th").length - 1) - (exceptColumn ? exceptColumn.length : 0), // col ( this means : merge as table column counts )
                        r: 0
                        // row
                    }
                }],
            };
            // WorkSheet
            var ws = XLSX2.utils.table_to_sheet(document.getElementById(tableId), opt);
            // new row --> Title
            ws["A1"] = {
                t: "s",
                v: excelTitle ? excelTitle : "엑셀 다운로드"
            };
            // new row --> style
            ws["A1"].s = {
                font: {
                    name: "arial",
                    bold: true,
                },
                alignment: {
                    vertical: "center",
                    horizontal: "center",
                    wrapText: '1', // any truthy value here
                },
                border: {
                    right: {
                        style: "thin",
                        color: "000000"
                    },
                    left: {
                        style: "thin",
                        color: "000000"
                    },
                    top: {
                        style: "thin",
                        color: "000000"
                    },
                },
            };
            // cell style
            for (i in ws) {
                if (typeof (ws[i]) != "object") continue;
                let cell = XLSX.utils.decode_cell(i);
                // cell style
                ws[i].s = {
                    font: {
                        name: "arial"
                    },
                    alignment: {
                        vertical: "center",
                        horizontal: "center",
                        wrapText: '1',
                    },
                    border: {
                        right: {
                            style: "thin",
                            color: "000000"
                        },
                        left: {
                            style: "thin",
                            color: "000000"
                        },
                        top: {
                            style: "thin",
                            color: "000000"
                        },
                        bottom: {
                            style: "thin",
                            color: "000000"
                        },
                    }
                };
                // new row & first row ( table th ) style
                if (cell.r == 0 || cell.r == 1) {
                    ws[i].s.fill = {
                        patternType: "solid",
                        fgColor: {
                            rgb: "b2b2b2"
                        },
                        bgColor: {
                            rgb: "b2b2b2"
                        }
                    };
                }
                // if you merge other rows use this
                /*
                 * if ( i == "!merges" ) { ws[ "!merges" ].push( { s : { c : 0 , r : 0 } , e : { c : 0 , r : 0 } } ); }
                 */
            }
            // Sheet Append With Title
            XLSX.utils.book_append_sheet(wb, ws, sheetTitle);
            // Create Excel File With File Name
            XLSX.writeFile(wb, (fileName + '.xlsx'));
        }
    });
});
