// Dashboard 1 Morris-chart


Morris.Area({
        element: 'extra-area-chart',
        data: [{
                    period: '2012',
                    Projects: 0,
                    Merchants: 0,
                    Customers: 0
                }, {
                    period: '2013',
                    Projects: 50,
                    Merchants: 15,
                    Customers: 5
                }, {
                    period: '2014',
                    Projects: 20,
                    Merchants: 50,
                    Customers: 65
                }, {
                    period: '2015',
                    Projects: 60,
                    Merchants: 12,
                    Customers: 7
                }, {
                    period: '2016',
                    Projects: 30,
                    Merchants: 20,
                    Customers: 120
                }, {
                    period: '2017',
                    Projects: 25,
                    Merchants: 80,
                    Customers: 40
                }, {
                    period: '2018',
                    Projects: 10,
                    Merchants: 10,
                    Customers: 10
                }


                ],
                lineColors: ['#f75b36', '#00b5c2', '#8698b7'],
                xkey: 'period',
                ykeys: ['Projects', 'Merchants', 'Customers'],
                labels: ['Projects', 'Merchants', 'Customers'],
                pointSize: 0,
                lineWidth: 0,
                resize:true,
                fillOpacity: 0.8,
                behaveLikeLine: true,
                gridLineColor: '#e0e0e0',
                hideHover: 'auto'
        
    });
