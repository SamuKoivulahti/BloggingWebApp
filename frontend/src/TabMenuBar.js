import React, {Component} from "react";
import {TabMenu} from 'primereact/tabmenu';

class TabMenuBar extends Component {
    constructor(properties) {
        super(properties)
        this.state = {activeItem: properties.items[0]};
        this.tabView = this.tabView.bind(this);
    }

    tabView(event) {
        this.setState({activeItem: event.value});
    }

    render() {
        return (
            <div>
                <TabMenu model={this.props.items} activeItem={this.state.activeItem} onTabChange={this.tabView}/>
                {this.state.activeItem.component}
            </div>
        );
    }
}
export default TabMenuBar