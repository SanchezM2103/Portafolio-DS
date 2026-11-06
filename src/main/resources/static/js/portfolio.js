const endpoints = {
    profile: '/api/profile', projects: '/api/projects', skills: '/api/skills',
    studies: '/api/studies', courses: '/api/courses', languages: '/api/languages',
    extraInfo: '/api/extra-info'
};

const byId = (id) => document.getElementById(id);
const setText = (element, value) => { element.textContent = value ?? ''; };
const clear = (element) => element.replaceChildren();

function createElement(tag, className, text) {
    const element = document.createElement(tag);
    if (className) element.className = className;
    if (text !== undefined && text !== null) element.textContent = text;
    return element;
}

function showEmpty(container, message, className = 'empty-state') {
    clear(container);
    container.append(createElement(className === 'panel-empty' ? 'p' : 'div', className, message));
}

async function fetchJson(url) {
    const response = await fetch(url, {headers: {Accept: 'application/json'}});
    if (!response.ok) throw new Error(`${response.status} ${response.statusText}`);
    return response.json();
}

function renderProfile(result) {
    if (result.status === 'rejected') {
        setText(byId('profile-status'), 'No se pudo cargar el perfil');
        setText(byId('profile-name'), 'Perfil no disponible');
        setText(byId('profile-response-status'), 'Error');
        setText(byId('profile-json'), result.reason.message);
        setText(byId('nav-name'), 'Portfolio');
        return;
    }
    const profile = result.value;
    const name = [profile.name, profile.surname].filter(Boolean).join(' ');
    const status = profile.workStatus === true ? 'Disponible para trabajar' : profile.workStatus === false ? 'No disponible actualmente' : '';
    setText(byId('profile-status'), status);
    setText(byId('profile-name'), name);
    setText(byId('profile-details'), Number.isFinite(profile.age) ? `${profile.age} años` : '');
    setText(byId('profile-response-status'), '200 OK');
    setText(byId('profile-json'), JSON.stringify(profile, null, 2));
    setText(byId('nav-name'), name);
    setText(byId('footer-name'), name);
    if (name) document.title = `${name} · Portfolio`;
}

function renderProjects(result) {
    const container = byId('projects-list');
    if (result.status === 'rejected') return showEmpty(container, `Error al cargar proyectos: ${result.reason.message}`);
    if (!result.value.length) return showEmpty(container, 'La API no ha devuelto proyectos.');
    clear(container);
    result.value.forEach((project, index) => {
        const card = createElement('article', 'project-card');
        const head = createElement('div', 'project-card-head');
        head.append(createElement('span', 'project-number', String(index + 1).padStart(2, '0')), createElement('span', 'project-icon', '↗'));
        card.append(head, createElement('div', 'project-label', 'PROYECTO'), createElement('h3', null, project.titleProject));
        if (project.desc) card.append(createElement('p', null, project.desc));
        if (project.url) {
            const footer = createElement('div', 'project-footer');
            const link = createElement('a', null, 'Ver proyecto ↗');
            link.href = project.url; link.target = '_blank'; link.rel = 'noopener noreferrer';
            footer.append(link); card.append(footer);
        }
        container.append(card);
    });
}

function renderSkills(result) {
    const container = byId('skills-list');
    const strip = byId('skill-strip');
    if (result.status === 'rejected') {
        showEmpty(container, `Error al cargar habilidades: ${result.reason.message}`);
        return showEmpty(strip, 'No se pudieron cargar las tecnologías.');
    }
    if (!result.value.length) {
        showEmpty(container, 'La API no ha devuelto habilidades.');
        return showEmpty(strip, 'No hay tecnologías disponibles.');
    }
    clear(container); clear(strip);
    result.value.forEach((skill) => {
        const pill = createElement('div', 'skill-pill');
        pill.append(createElement('span', 'skill-indicator', '◆'), createElement('span', null, skill.name));
        if (skill.level) pill.append(createElement('small', null, skill.level));
        container.append(pill); strip.append(createElement('span', null, skill.name));
    });
}

function appendTimeline(container, date, title, description) {
    const item = createElement('div', 'timeline');
    const content = createElement('div');
    if (date) content.append(createElement('div', 'item-date', date));
    content.append(createElement('h4', null, title));
    if (description) content.append(createElement('p', null, description));
    item.append(createElement('span', 'timeline-dot'), content); container.append(item);
}

function renderStudies(result) {
    const container = byId('studies-list');
    if (result.status === 'rejected') return showEmpty(container, `Error al cargar estudios: ${result.reason.message}`, 'panel-empty');
    if (!result.value.length) return showEmpty(container, 'La API no ha devuelto estudios.', 'panel-empty');
    clear(container);
    result.value.forEach((study) => appendTimeline(container, [study.startYear, study.endYear].filter(Boolean).join(' — '), study.title, study.university));
}

function renderCourses(result) {
    const container = byId('courses-list');
    if (result.status === 'rejected') return showEmpty(container, `Error al cargar cursos: ${result.reason.message}`, 'panel-empty');
    if (!result.value.length) return showEmpty(container, 'La API no ha devuelto cursos.', 'panel-empty');
    clear(container);
    result.value.forEach((course) => appendTimeline(container, course.dateCompletion, course.name, course.organization));
}

function renderLanguages(result) {
    const container = byId('languages-list');
    if (result.status === 'rejected') return showEmpty(container, `Error al cargar idiomas: ${result.reason.message}`);
    if (!result.value.length) return showEmpty(container, 'La API no ha devuelto idiomas.');
    clear(container); container.append(createElement('span', 'languages-title', 'IDIOMAS'));
    result.value.forEach((language) => {
        const chip = createElement('span', 'language-chip');
        chip.append(createElement('strong', null, language.language));
        if (language.languageLevel) chip.append(createElement('small', null, language.languageLevel));
        container.append(chip);
    });
}

function renderExtraInfo(result) {
    const container = byId('extra-info-list');
    if (result.status === 'rejected') return showEmpty(container, `Error al cargar información: ${result.reason.message}`);
    if (!result.value.length) return showEmpty(container, 'La API no ha devuelto información adicional.');
    clear(container);
    result.value.forEach((info) => {
        const card = createElement('article', 'project-card');
        if (info.year) card.append(createElement('div', 'item-date', info.year));
        card.append(createElement('h3', null, info.title));
        if (info.desc) card.append(createElement('p', null, info.desc));
        container.append(card);
    });
}

function renderEndpointList() {
    const container = byId('endpoint-list');
    Object.values(endpoints).forEach((url) => {
        const row = createElement('div');
        const link = createElement('a', null, url); link.href = url;
        row.append(createElement('span', null, 'GET'), link); container.append(row);
    });
}

async function loadPortfolio() {
    const keys = Object.keys(endpoints);
    const results = await Promise.allSettled(Object.values(endpoints).map(fetchJson));
    const data = Object.fromEntries(keys.map((key, index) => [key, results[index]]));
    renderProfile(data.profile); renderProjects(data.projects); renderSkills(data.skills);
    renderStudies(data.studies); renderCourses(data.courses); renderLanguages(data.languages);
    renderExtraInfo(data.extraInfo);
}

setText(byId('current-year'), new Date().getFullYear());
renderEndpointList();
loadPortfolio();
