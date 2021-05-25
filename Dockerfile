FROM node:14-alpine3.10 as builder
WORKDIR /usr/src/app

COPY package*.json ./
RUN NG_CLI_ANALYTICS=false npm install

COPY . .
RUN npm run build --prod

# Production build ready, setting up nginx
FROM nginx
COPY --from=builder /usr/src/app/dist /usr/share/nginx/html

RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD [ "nginx", "-g", "daemon off;" ]
