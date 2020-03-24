import React, { Component } from 'react'
import ApiService from "../../service/ApiService";

//Our main component class which invokes the APIs on loading and populates the table with relevant values.

class ListUserComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            ageRows: [],
			genRows:[],
            message: null
        }
        this.reloadUserList = this.reloadUserList.bind(this);
    }

    componentDidMount() {
        this.reloadUserList();
    }

    reloadUserList() {
        ApiService.fetchAgeAvg()
            .then((res) => {
                this.setState({ageRows: res.data})
            });
		ApiService.fetchGenderAvg()
            .then(res => {
                this.setState({genRows: res.data})
            });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Question vs Average(Data Value) By Year for all age groups</h2>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>Question</th>
			    <th>Year</th>
                            <th>Average(Data Value)</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.ageRows.map(
                        row =>
                                    <tr>
                                        <td>{row.question}</td>
					<td>{row.year}</td>
                                        <td>{row.average}</td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
				
				<h2 className="text-center">Question vs Average(Data Value) By Year for Women</h2>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>Question</th>
			    <th>Year</th>
                            <th>Average(Data Value)</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.genRows.map(
                        row =>
                                    <tr>
                                        <td>{row.question}</td>
					<td>{row.year}</td>
                                        <td>{row.average}</td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>


            </div>
        );
    }

}

export default ListUserComponent;