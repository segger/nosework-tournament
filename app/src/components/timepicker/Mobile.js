import React, { Component } from 'react';
import DatePicker from 'react-mobile-datepicker';

class TimepickerMobile extends Component {
    state = {
        time: new Date(),
        isOpen: false,
    };

    handleClick = () => {
        this.setState({ isOpen: true });
    };

    handleCancel = () => {
        this.setState({ isOpen: false });
    };

    handleSelect = (time) => {
        console.log('selected: ' + this.state.time);
        this.setState({ time, isOpen: false });
    };

    render() {
        return (
            <div>
                <input type="time" name="time" id="time" placeholder="00:00" value={this.state.time} onClick={this.handleClick} readOnly={true}/>

                <DatePicker
                    value={this.state.time}
                    dateFormat={['mm','ss']}
                    showFormat="mm:ss"
                    showHeader={true}
                    confirmText="Ok"
                    cancelText="Cancel"
                    isOpen={this.state.isOpen}
                    onSelect={this.handleSelect}
                    onCancel={this.handleCancel} />
            </div>
        );
    }
}

export default TimepickerMobile;