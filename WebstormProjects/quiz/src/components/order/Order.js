import React, { Component } from "react";
import "./order.css";
import { Table, message, Button } from "antd";
import "antd/dist/antd.css";
import axios from "axios";

class Order extends Component {
    constructor(props) {
        super(props);
        this.state = {
            order: [],
            isLoading: false 
        }
    }

    getOrderData = () => {
        fetch("http://localhost:8080/orders", { method: "GET" })
            .then(response => {
                return response.json();
            })
            .then(data => {
                data.map((item, index) => {
                    return item["key"] = index.toString()
                });
                this.setState({
                    order: data
                });
            })
            .catch(() => {
                message.info("订单加载失败，请重新加载！")
            })
    }

    componentDidMount() {
        this.getOrderData();
    }

    handleDeleteClick = (id) => {
        this.setState({
            isLoading: true
        });

        axios({
            url: `http://localhost:8080/order/${id}`,
            method: "delete"
        })
            .then(() => {
                message.info("订单删除成功！")
                this.getOrderData();
            })
            .then(() => { 
                this.setState({
                    isLoading: false
                })
            })
            .catch(() => {
                message.info("删除失败，请稍后再试！");
                this.setState({
                    isLoading: false
                })
            })
    }

    render() {
        const columns = [
            {
                title: "名字",
                dataIndex: "name",
                key: "name"
            },
            {
                title: "单价",
                dataIndex: "price",
                key: "price"
            },
            {
                title: "数量",
                dataIndex: "count",
                key: "count"
            },
            {
                title: "单位",
                dataIndex: "unit",
                key: "unit"
            },
            {
                title: "操作",
                key: "action",
                render: (text, record) => {
                    return (<Button
                        className="deleteButton"
                        disabled={this.state.isLoading}
                        onClick={() => this.handleDeleteClick(record.id)}
                    >
                        删除
                    </Button>)
                }
            }
        ];

        const pagination = this.state.order.length <= 6 ? false : { pageSize: 5, disabled: false }

        return (
            <div className="order">
                <h2>商品列表</h2>
                {
                    this.state.order.length === 0
                        ? <h4>暂无订单， 返回商城页面继续购买</h4>
                        : <Table
                            columns={columns}
                            dataSource={this.state.order}
                            pagination={pagination}
                            tableLayout="fixed"
                        ></Table>
                }


            </div>
        );
    }
}

export default Order;