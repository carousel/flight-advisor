# FlightAdvisor app
## Users
    Admin
        seed data
    Regular user
        manual registration
## Use cases
### Admin
        Admin can add cities
        Admin can import/upload data for airports and routes (if city exists)
### Regular user
        Has to register
        Can fetch all cities
            with comments
        Search cities by name
            with comments(if specified)
        CRUD on city comments
        Find a cheapest flight
# REST Resources
    city/cities
        comments
    flight
    airports
    routes
# Endpoints for resources
        ADMIN
            endpoints:
                create city
                    method POST
                    /cities
                import data
                    method POST
                    /routes
                    /airports
        USER
            endpoints:
                get all citites(with comments)
                    method GET
                    /cities
                search for cities
                    method GET
                    /cities?q=name(with comments)
                comments
                    create new comment for city
                        method POST
                        /cities/{id}/comments
                    delete a comment for the city
                        method DELETE
                        /cities/{id}/comments/{id}
                    update a comment for the city
                        method PUT
                        /cities/{id}/comments/{id}
                find cheapest flight
### Possible entities/tables
    User
        type
            admin
            user
    City
        CityComment
    Airport
        City?
    Route
        SrcAirport
        DestAirport
# Dataset
## Example AIRPORT data
    Airport ID
        1 
            (Identifier for the airportBean)
    Name
        "Goroka Airport" 
            (Name of airportBean)
    City
        "Goroka" 
            (Main city served by airportBean)
    Country
        "Papua New Guinea" 
            (Country or territory where airportBean is located)
    IATA
        "GKA"
            (3-letter IATA code. Null if not assigned/unknown)
    ICAO
        "AYGA"
            (4-letter ICAO code. Null if not assigned)
    Latitude
        -6.081689834590001
            (Decimal degrees, usually to six significant digits)
    Longitude
        145.391998291
            (Decimal degrees, usually to six significant digits)
    Altitude
        5282
            (In feet)
    Timezone
        10
            (Hours offset from UTC)
    DST
        "U"
            (Daylight savings time. One of E (Europe), A (US/Canada), S (South America), O (Australia), Z (New Zealand), N (None) or U (Unknown))
    Tz
        "Pacific/Port_Moresby"
            (Timezone in "tz" (Olson) format, eg. "America/Los_Angeles")
    Type
        "airportBean"
            (Type of the airportBean)
    Source
        "OurAirports"
            (Source of this data)
## Example ROUTE data
    Airline
        2B 
            (2-letter (IATA) or 3-letter (ICAO) code of the airline)
    Airline ID
        410 
            (Identifier for airline)
    Source airportBean
        AER 
            (3-letter (IATA) or 4-letter (ICAO) code of the source airportBean)
    Source airportBean ID
        2965 
            (Identifier for source airportBean)
    Destination airportBean
        KZN 
            (3-letter (IATA) or 4-letter (ICAO) code of the destination airportBean)
    Destination airportBean ID
        2990 
            (Unique OpenFlights identifier for destination airportBean)
    Codeshare 
        ''  
            ("Y" if this flight is a codeshare, empty otherwise)
    Stops
        0 (Number of stops on this flight ("0" for direct))
    Equipment
        CR2 
            (3-letter codes for plane type(s) generally used on this flight, separated by spaces)
    Price
        95.87 
            (Flight cost)
# Possible dependencies
    feign/ribbon
    swagger
    actuator
    config server

# Todo

    cheapest flight
    clean
    exceptions/optional
    tests
    code comments
    sonar lint
    refactor jwt
        @ManyToMany
        simplify
    external config?
    separate data service?
    city comments
    search cities(for user)
    user signup
    upload/read file @Async
    import/upload airports/routes
    replace @Autowired with constructor injection

# Comments
    url to BCrypt salt 
