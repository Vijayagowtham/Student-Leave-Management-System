// config.js
// Centralized Configuration File for API Endpoints

const CONFIG = {
    API_BASE_URL: 'http://localhost:8080'
};
// Expose it globally
window.CONFIG = CONFIG;

// Intercept global fetch to transparently append JWT Authorization headers
const originalFetch = window.fetch;
window.fetch = async function () {
    let [resource, config] = arguments;

    // Only intercept paths that go to our backend
    if (typeof resource === 'string' && resource.includes(CONFIG.API_BASE_URL)) {
        const token = localStorage.getItem('jwtToken');

        if (token) {
            if (!config) {
                config = {};
            }
            if (!config.headers) {
                config.headers = {};
            }
            if (config.headers instanceof Headers) {
                config.headers.append('Authorization', 'Bearer ' + token);
            } else {
                config.headers['Authorization'] = 'Bearer ' + token;
            }
        }
    }
    return originalFetch(resource, config);
};
