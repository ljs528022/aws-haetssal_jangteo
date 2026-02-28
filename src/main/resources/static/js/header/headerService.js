const headerService = (() => {
    const logoutHandler = () => {
        const user = sessionStorage.getItem("user");
        console.log(user);
    }

    return {
        logoutHandler: logoutHandler,
    };
})();