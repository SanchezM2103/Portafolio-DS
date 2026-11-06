const menuButton = document.querySelector('.menu-toggle');
const menu = document.querySelector('.nav-menu');

if (menuButton && menu) {
    menuButton.addEventListener('click', () => {
        const isOpen = menuButton.getAttribute('aria-expanded') === 'true';
        menuButton.setAttribute('aria-expanded', String(!isOpen));
        menu.classList.toggle('is-open', !isOpen);
    });

    menu.querySelectorAll('a').forEach((link) => {
        link.addEventListener('click', () => {
            menuButton.setAttribute('aria-expanded', 'false');
            menu.classList.remove('is-open');
        });
    });
}

if ('IntersectionObserver' in window) {
    const observer = new IntersectionObserver((entries) => {
        entries.forEach((entry) => {
            if (entry.isIntersecting) {
                entry.target.classList.add('is-visible');
                observer.unobserve(entry.target);
            }
        });
    }, {threshold: 0.12});

    document.querySelectorAll('.reveal').forEach((element) => observer.observe(element));
} else {
    document.querySelectorAll('.reveal').forEach((element) => element.classList.add('is-visible'));
}

document.querySelectorAll('.project-media img').forEach((image) => {
    image.addEventListener('error', () => {
        image.closest('.project-media')?.classList.add('image-error');
        image.remove();
    });
});
